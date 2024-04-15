/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DikamonTu
 */
@Entity
@Table(name = "phuong_xa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhuongXa.findAll", query = "SELECT p FROM PhuongXa p"),
    @NamedQuery(name = "PhuongXa.findById", query = "SELECT p FROM PhuongXa p WHERE p.id = :id"),
    @NamedQuery(name = "PhuongXa.findByTen", query = "SELECT p FROM PhuongXa p WHERE p.ten = :ten")})
public class PhuongXa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "ten")
    private String ten;
    @JoinColumn(name = "id_quanHuyen", referencedColumnName = "id")
    @ManyToOne
    private QuanHuyen idquanHuyen;

    public PhuongXa() {
    }

    public PhuongXa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public QuanHuyen getIdquanHuyen() {
        return idquanHuyen;
    }

    public void setIdquanHuyen(QuanHuyen idquanHuyen) {
        this.idquanHuyen = idquanHuyen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhuongXa)) {
            return false;
        }
        PhuongXa other = (PhuongXa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dnt.pojo.PhuongXa[ id=" + id + " ]";
    }
    
}
