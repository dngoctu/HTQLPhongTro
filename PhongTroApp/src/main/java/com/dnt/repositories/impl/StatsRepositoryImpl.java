/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories.impl;

import com.dnt.pojo.ChuTro;
import com.dnt.pojo.NguoiThue;
import com.dnt.repositories.StatsRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
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
public class StatsRepositoryImpl implements StatsRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Object statsByNguoiThue() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(NguoiThue.class);
        q.select(b.count(r));

        Query query = s.createQuery(q);
        
        return query.getSingleResult();
    }

    @Override
    public Object statsByChuTro() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(ChuTro.class);
        q.select(b.count(r));

        Query query = s.createQuery(q);
        
        return query.getSingleResult();
    }

    @Override
    public List<Object[]> statsNguoiThueByPeriod(int time, String period) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root r = q.from(NguoiThue.class);


        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(b.function("YEAR", Integer.class, r.get("ngayTao")), time));

        q.where(predicates.toArray(Predicate[]::new));
        q.groupBy(b.function(period, Integer.class, r.get("ngayTao")));
        q.multiselect(b.function(period, Integer.class, r.get("ngayTao")), b.count(r));

        
        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Object[]> statsChuTroByPeriod(int time, String period) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root r = q.from(ChuTro.class);


        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(b.function("YEAR", Integer.class, r.get("ngayTao")), time));

        q.where(predicates.toArray(Predicate[]::new));
        q.groupBy(b.function(period, Integer.class, r.get("ngayTao")));
        q.multiselect(b.function(period, Integer.class, r.get("ngayTao")), b.count(r));

        
        Query query = s.createQuery(q);

        return query.getResultList();
    }
    
    
    
}
