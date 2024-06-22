/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories.impl;

import com.dnt.pojo.KinhdoVido;
import com.dnt.repositories.KinhdoVidoRepository;
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
public class KinhdoVidoRepositoryImpl implements KinhdoVidoRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<KinhdoVido> getKinhdoVido(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<KinhdoVido> q = b.createQuery(KinhdoVido.class);
        Root r = q.from(KinhdoVido.class);
        q.select(r);
        
        List<Predicate> predicates = new ArrayList<>();

        String kinhdo = params.get("kinhdo");
        if (kinhdo != null && !kinhdo.isEmpty()) {
            predicates.add(b.like(r.get("kinhDo"), "%" + kinhdo + "%"));
        }
        
        String vido = params.get("vido");
        if (vido != null && !vido.isEmpty()) {
            predicates.add(b.like(r.get("viDo"), "%" + vido + "%"));
        }
        
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);
        
        List<KinhdoVido> kinhdoVidos = query.getResultList();
        
        return kinhdoVidos;
    }

    @Override
    public void addOrUpdate(KinhdoVido kv) {
        Session s = this.factory.getObject().getCurrentSession();
        if (kv.getId() != null)
            s.update(kv);
        else
            s.save(kv);
    }

    @Override
    public KinhdoVido getKinhdoVidoById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(KinhdoVido.class, id);
    }

    @Override
    public void deleteKinhdoVido(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        KinhdoVido q = this.getKinhdoVidoById(id);
        s.delete(q);
    }
    
}
