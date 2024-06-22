/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories.impl;

import com.dnt.pojo.QuanHuyen;
import com.dnt.repositories.QuanHuyenRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
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
public class QuanHuyenRepositoryImpl implements QuanHuyenRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<QuanHuyen> getQuanHuyen(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<QuanHuyen> q = b.createQuery(QuanHuyen.class);
        Root r = q.from(QuanHuyen.class);
        q.select(r);
        
        List<Predicate> predicates = new ArrayList<>();

        String quanhuyen = params.get("quanhuyen");
        if (quanhuyen != null && !quanhuyen.isEmpty()) {
            predicates.add(b.like(r.get("ten"), "%" + quanhuyen + "%"));
        }
        
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);
        
        List<QuanHuyen> quanHuyens = query.getResultList();
        
        return quanHuyens;
    }

    @Override
    public void addOrUpdate(QuanHuyen q) {
        Session s = this.factory.getObject().getCurrentSession();
        if (q.getId() != null)
            s.update(q);
        else
            s.save(q);
    }

    @Override
    public QuanHuyen getQuanHuyenById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(QuanHuyen.class, id);}

    @Override
    public void deleteQuanHuyen(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        QuanHuyen q = this.getQuanHuyenById(id);
        s.delete(q);}
    
}
