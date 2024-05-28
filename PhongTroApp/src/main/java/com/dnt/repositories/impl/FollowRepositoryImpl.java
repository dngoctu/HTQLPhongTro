/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories.impl;

import com.dnt.pojo.Follow;
import com.dnt.repositories.FollowRepository;
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
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DikamonTu
 */
@Repository
@Transactional
public class FollowRepositoryImpl implements FollowRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Follow> getFollow(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Follow> q = b.createQuery(Follow.class);
        Root r = q.from(Follow.class);
        q.select(r);
        
        List<Predicate> predicates = new ArrayList<>();

        String idChutro = params.get("chutro");
        if (idChutro != null && !idChutro.isEmpty()) {
            try {
                int chutroId = Integer.parseInt(idChutro);
                predicates.add(b.equal(r.get("idchuTro").get("id"), chutroId));
            } catch (NumberFormatException e) {
                System.err.println("Invalid chutro ID format: " + idChutro);
            }
        }
        
        String idNguoiThue = params.get("nguoithue");
        if (idNguoiThue != null && !idNguoiThue.isEmpty()) {
            try {
                int nguoithueId = Integer.parseInt(idNguoiThue);
                predicates.add(b.equal(r.get("idnguoiThue").get("id"), nguoithueId));
            } catch (NumberFormatException e) {
                System.err.println("Invalid nguoithue ID format: " + idNguoiThue);
            }
        }
        
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);

        List<Follow> follows = query.getResultList();
        
        return follows;
    }

    @Override
    public void addOrUpdate(Follow f) {
        Session s = this.factory.getObject().getCurrentSession();
        if (f.getId() != null)
            s.update(f);
        else
            s.save(f);
    }

    @Override
    public Follow getFollowById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Follow.class, id);
    }

    @Override
    public void deleteFollow(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Follow n = this.getFollowById(id);
        s.delete(n);
    }
    
}
