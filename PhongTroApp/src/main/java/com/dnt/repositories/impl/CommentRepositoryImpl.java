/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories.impl;

import com.dnt.pojo.Comment;
import com.dnt.repositories.CommentRepository;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author DikamonTu
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class CommentRepositoryImpl implements CommentRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    
    @Override
    public List<Comment> getComment(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Comment> q = b.createQuery(Comment.class);
        Root r = q.from(Comment.class);
        q.select(r);
        
        List<Predicate> predicates = new ArrayList<>();

        String idTaiKhoan = params.get("taikhoan");
        if (idTaiKhoan != null && !idTaiKhoan.isEmpty()) {
            try {
                int taiKhoanId = Integer.parseInt(idTaiKhoan);
                predicates.add(b.equal(r.get("idtaiKhoan").get("id"), taiKhoanId));
            } catch (NumberFormatException e) {
                System.err.println("Invalid taiKhoan ID format: " + idTaiKhoan);
            }
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
        
        List<Comment> comments = query.getResultList();
        
        return comments;
    }

    @Override
    public void addOrUpdate(Comment c) {
    Session s = this.factory.getObject().getCurrentSession();
        if (c.getId() != null)
            s.update(c);
        else
            c.setThoiGian(new Date());
            s.save(c);
    }

    @Override
    public Comment getCommentById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Comment.class, id);
    }

    @Override
    public void deleteComment(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Comment n = this.getCommentById(id);
        s.delete(n);
    }
    
}
