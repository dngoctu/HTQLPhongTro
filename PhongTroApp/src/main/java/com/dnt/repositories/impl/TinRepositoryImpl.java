/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories.impl;

import com.dnt.pojo.Comment;
import com.dnt.pojo.Tin;
import com.dnt.repositories.TinRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DikamonTu
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class TinRepositoryImpl implements TinRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    
    @Override
    public List<Tin> getTin(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Tin> q = b.createQuery(Tin.class);
        Root r = q.from(Tin.class);
        q.select(r);
        
        List<Predicate> predicates = new ArrayList<>();

        String loaiTin = params.get("loaitin");
        if (loaiTin != null && !loaiTin.isEmpty()) {
            predicates.add(b.like(r.get("loaiTin"), "%" + loaiTin + "%"));
        }
        
        String tenChutro = params.get("chutro");
        if (tenChutro != null && !tenChutro.isEmpty()) {
            Expression<String> fullName = b.concat(r.get("idchuTro").get("ho"), " ");
            fullName = b.concat(fullName, r.get("idchuTro").get("ten"));

            predicates.add(b.like(fullName, "%" + tenChutro + "%"));
        }
        
        String tenNguoiThue = params.get("nguoithue");
        if (tenNguoiThue != null && !tenNguoiThue.isEmpty()) {
            Expression<String> fullName = b.concat(r.get("idnguoiThue").get("ho"), " ");
            fullName = b.concat(fullName, r.get("idnguoiThue").get("ten"));

            predicates.add(b.like(fullName, "%" + tenNguoiThue + "%"));
        }
        

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("nguoithue.pageSize").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
        
        List<Tin> tins = query.getResultList();
        
        return tins;
    }

    @Override
    public void addOrUpdate(Tin t) {
        Session s = this.factory.getObject().getCurrentSession();
        if (t.getId() != null)
            s.update(t);
        else
            t.setThoiGian(new Date());
            s.save(t);}

    @Override
    public Tin getTinById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Tin.class, id);
    }

    @Override
    public void deleteTin(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Tin t = this.getTinById(id);
        s.delete(t);
    }
    
}
