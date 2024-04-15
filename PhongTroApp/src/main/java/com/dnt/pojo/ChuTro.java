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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DikamonTu
 */
@Entity
@Table(name = "chu_tro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChuTro.findAll", query = "SELECT c FROM ChuTro c"),
    @NamedQuery(name = "ChuTro.findById", query = "SELECT c FROM ChuTro c WHERE c.id = :id"),
    @NamedQuery(name = "ChuTro.findByDiaChi", query = "SELECT c FROM ChuTro c WHERE c.diaChi = :diaChi"),
    @NamedQuery(name = "ChuTro.findBySdt", query = "SELECT c FROM ChuTro c WHERE c.sdt = :sdt")})
public class ChuTro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "diaChi")
    private String diaChi;
    @Size(max = 12)
    @Column(name = "sdt")
    private String sdt;
    @OneToMany(mappedBy = "idchuTro")
    private Set<PhongTro> phongTroSet;
    @OneToMany(mappedBy = "idchuTro")
    private Set<Follow> followSet;
    @OneToMany(mappedBy = "idchuTro")
    private Set<Tin> tinSet;
    @OneToMany(mappedBy = "idchuTro")
    private Set<Comment> commentSet;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TaiKhoan taiKhoan;

    public ChuTro() {
    }

    public ChuTro(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @XmlTransient
    public Set<PhongTro> getPhongTroSet() {
        return phongTroSet;
    }

    public void setPhongTroSet(Set<PhongTro> phongTroSet) {
        this.phongTroSet = phongTroSet;
    }

    @XmlTransient
    public Set<Follow> getFollowSet() {
        return followSet;
    }

    public void setFollowSet(Set<Follow> followSet) {
        this.followSet = followSet;
    }

    @XmlTransient
    public Set<Tin> getTinSet() {
        return tinSet;
    }

    public void setTinSet(Set<Tin> tinSet) {
        this.tinSet = tinSet;
    }

    @XmlTransient
    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
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
        if (!(object instanceof ChuTro)) {
            return false;
        }
        ChuTro other = (ChuTro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dnt.pojo.ChuTro[ id=" + id + " ]";
    }
    
}
