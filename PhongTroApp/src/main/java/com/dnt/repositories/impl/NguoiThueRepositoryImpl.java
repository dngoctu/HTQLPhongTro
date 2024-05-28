package com.dnt.repositories.impl;


import com.dnt.pojo.NguoiThue;
import com.dnt.repositories.NguoiThueRepository;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DikamonTu
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class NguoiThueRepositoryImpl implements NguoiThueRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    
    @Override
    public List<NguoiThue> getNguoiThue(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<NguoiThue> q = b.createQuery(NguoiThue.class);
        Root r = q.from(NguoiThue.class);
        q.select(r);
        
        List<Predicate> predicates = new ArrayList<>();

        String ten = params.get("ten");
        if (ten != null && !ten.isEmpty()) {
            Predicate likeHo = b.like(r.get("ho"), String.format("%%%s%%", ten));
            Predicate likeTen = b.like(r.get("ten"), String.format("%%%s%%", ten));
            predicates.add(b.or(likeHo, likeTen));
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
        
        List<NguoiThue> nguoithues = query.getResultList();

        
        return nguoithues;
    }
    @Override
    public void addOrUpdateNguoiThue(NguoiThue n) {
        Session s = this.factory.getObject().getCurrentSession();
        if (n.getId() != null)
            s.update(n);
        else
            s.save(n);
    }
    @Override
    public NguoiThue getNguoiThueById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(NguoiThue.class, id);
    }

    @Override
    public void deleteNguoiThue(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        NguoiThue n = this.getNguoiThueById(id);
        s.delete(n);
    }

    
    
}
