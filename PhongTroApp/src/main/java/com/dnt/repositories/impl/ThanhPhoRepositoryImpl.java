/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories.impl;

import com.dnt.pojo.ThanhPho;
import com.dnt.pojo.Tin;
import com.dnt.repositories.ThanhPhoRepository;
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
public class ThanhPhoRepositoryImpl implements ThanhPhoRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    
    @Override
    public List<ThanhPho> getThanhPho(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ThanhPho> q = b.createQuery(ThanhPho.class);
        Root r = q.from(ThanhPho.class);
        q.select(r);
        
        List<Predicate> predicates = new ArrayList<>();

        String thanhPho = params.get("thanhPho");
        if (thanhPho != null && !thanhPho.isEmpty()) {
            predicates.add(b.like(r.get("ten"), "%" + thanhPho + "%"));
        }
        
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("nguoithue.pageSize"));
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
        
        List<ThanhPho> thanhPhos = query.getResultList();
        
        return thanhPhos;
    }

    @Override
    public void addOrUpdate(ThanhPho tp) {
        Session s = this.factory.getObject().getCurrentSession();
        if (tp.getId() != null)
            s.update(tp);
        else
            s.save(tp);
    }

    @Override
    public ThanhPho getThanhPhoById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(ThanhPho.class, id);
    }

    @Override
    public void deleteThanhPho(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        ThanhPho tp = this.getThanhPhoById(id);
        s.delete(tp);
    }
    
}
