/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "quan_huyen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuanHuyen.findAll", query = "SELECT q FROM QuanHuyen q"),
    @NamedQuery(name = "QuanHuyen.findById", query = "SELECT q FROM QuanHuyen q WHERE q.id = :id"),
    @NamedQuery(name = "QuanHuyen.findByTen", query = "SELECT q FROM QuanHuyen q WHERE q.ten = :ten")})
public class QuanHuyen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "ten")
    private String ten;
    @OneToMany(mappedBy = "idQuan")
    private Set<PhongTro> phongTroSet;
    @JoinColumn(name = "id_thanhPho", referencedColumnName = "id")
    @ManyToOne
    private ThanhPho idthanhPho;

    public QuanHuyen() {
    }

    public QuanHuyen(Integer id) {
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
    public Set<PhongTro> getPhongTroSet() {
        return phongTroSet;
    }

    public void setPhongTroSet(Set<PhongTro> phongTroSet) {
        this.phongTroSet = phongTroSet;
    }

    public ThanhPho getIdthanhPho() {
        return idthanhPho;
    }

    public void setIdthanhPho(ThanhPho idthanhPho) {
        this.idthanhPho = idthanhPho;
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
        if (!(object instanceof QuanHuyen)) {
            return false;
        }
        QuanHuyen other = (QuanHuyen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dnt.pojo.QuanHuyen[ id=" + id + " ]";
    }
    
}
