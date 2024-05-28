/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories.impl;

import com.dnt.pojo.TaiKhoan;
import com.dnt.repositories.TaiKhoanRepository;
import java.util.List;
import javax.persistence.Query;
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
public class TaiKhoanRepositoryImpl implements TaiKhoanRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public TaiKhoan getTaiKhoanByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("TaiKhoan.findByUsername");
        q.setParameter("username", username);
        
        return (TaiKhoan) q.getSingleResult();
    }

    @Override
    public TaiKhoan getTaiKhoanById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("TaiKhoan.findById");
        q.setParameter("id", id);
        
        return (TaiKhoan) q.getSingleResult();
    }
    
    @Override
    public void addTaiKhoan(TaiKhoan taikhoan) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(taikhoan);
    }

    @Override
    public List<TaiKhoan> getTaiKhoan() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("TaiKhoan.findAll");
        
        return q.getResultList();
    }
    
}
