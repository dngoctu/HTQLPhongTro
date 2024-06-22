/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DikamonTu
 */
@Entity
@Table(name = "thanh_pho")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ThanhPho.findAll", query = "SELECT t FROM ThanhPho t"),
    @NamedQuery(name = "ThanhPho.findById", query = "SELECT t FROM ThanhPho t WHERE t.id = :id"),
    @NamedQuery(name = "ThanhPho.findByTen", query = "SELECT t FROM ThanhPho t WHERE t.ten = :ten")})
public class ThanhPho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "ten")
    private String ten;
    @OneToMany(mappedBy = "idthanhPho")
    @JsonIgnore
    private Set<QuanHuyen> quanHuyenSet;

    public ThanhPho() {
    }

    public ThanhPho(Integer id) {
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

    @XmlTransient
    public Set<QuanHuyen> getQuanHuyenSet() {
        return quanHuyenSet;
    }

    public void setQuanHuyenSet(Set<QuanHuyen> quanHuyenSet) {
        this.quanHuyenSet = quanHuyenSet;
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
        if (!(object instanceof ThanhPho)) {
            return false;
        }
        ThanhPho other = (ThanhPho) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dnt.pojo.ThanhPho[ id=" + id + " ]";
    }
    
}
