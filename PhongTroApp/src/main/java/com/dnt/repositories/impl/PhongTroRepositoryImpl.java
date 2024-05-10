/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories.impl;

import com.dnt.pojo.PhongTro;
import com.dnt.repositories.PhongTroRepository;
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
public class PhongTroRepositoryImpl implements PhongTroRepository{
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<PhongTro> getPhongTro(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<PhongTro> q = b.createQuery(PhongTro.class);
        Root r = q.from(PhongTro.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String diaChiPhong = params.get("diaChiPhong");
        if (diaChiPhong != null && !diaChiPhong.isEmpty()) {
            predicates.add(b.like(r.get("diaChiPhong"), String.format("%%%s%%", diaChiPhong)));
        }
        
        String fromGia = params.get("fromGia");
        if (fromGia != null && !fromGia.isEmpty()) {
            predicates.add(b.greaterThanOrEqualTo(r.get("gia"), Double.parseDouble(fromGia)));
        }

        String toGia = params.get("toGia");
        if (toGia != null && !toGia.isEmpty()) {
            predicates.add(b.lessThanOrEqualTo(r.get("gia"), Double.parseDouble(toGia)));
        }
        
        String conTrong = params.get("conTrong");
        if (conTrong != null && !conTrong.isEmpty()) {
            predicates.add(b.equal(r.get("conTrong"), conTrong));
        }

        String chuTroId = params.get("chuTroId");
        if (chuTroId != null && !chuTroId.isEmpty()) {
            predicates.add(b.equal(r.get("idchuTro"), Integer.parseInt(chuTroId)));
        }
        
        String thanhPhoId = params.get("thanhPhoId");
        if (thanhPhoId != null && !thanhPhoId.isEmpty()) {
            predicates.add(b.equal(r.get("idthanhPho"), Integer.parseInt(thanhPhoId)));
        }
                

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);
        
        
//        String p = params.get("page");
//        if (p != null && !p.isEmpty()) {
//            int pageSize = Integer.parseInt(env.getProperty("products.pageSize").toString());
//            int start = (Integer.parseInt(p) - 1) * pageSize;
//            query.setFirstResult(start);
//            query.setMaxResults(pageSize);
//        }
        
        List<PhongTro> phongTros = query.getResultList();

        return phongTros;
    }

    @Override
    public void addOrUpdate(PhongTro p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PhongTro getPhongTroById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletePhongTro(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
