/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories.impl;

import com.dnt.pojo.HinhAnhTro;
import com.dnt.repositories.HinhAnhTroRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DikamonTu
 */
@Repository
@Transactional
public class HinhAnhTroRepositoryImpl implements HinhAnhTroRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<HinhAnhTro> getHinhAnhTro(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<HinhAnhTro> q = b.createQuery(HinhAnhTro.class);
        Root r = q.from(HinhAnhTro.class);
        q.select(r);
        
        List<Predicate> predicates = new ArrayList<>();
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);
        
        List<HinhAnhTro> hinhanhs = query.getResultList();
        
        return hinhanhs;
    }

    @Override
    public void addOrUpdate(HinhAnhTro hinhanh) {
        Session s = this.factory.getObject().getCurrentSession();
        if (hinhanh.getId() != null)
            s.update(hinhanh);
        else
            s.save(hinhanh);
    }

    @Override
    public HinhAnhTro getHinhAnhTroById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(HinhAnhTro.class, id);
    
    }

    @Override
    public void deleteHinhAnhTro(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        HinhAnhTro h = this.getHinhAnhTroById(id);
        s.delete(h);
    }
}
