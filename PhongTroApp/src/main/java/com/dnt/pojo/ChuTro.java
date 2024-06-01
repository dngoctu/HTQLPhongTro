/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "ChuTro.findByHo", query = "SELECT c FROM ChuTro c WHERE c.ho = :ho"),
    @NamedQuery(name = "ChuTro.findByTen", query = "SELECT c FROM ChuTro c WHERE c.ten = :ten"),
    @NamedQuery(name = "ChuTro.findByDiaChi", query = "SELECT c FROM ChuTro c WHERE c.diaChi = :diaChi"),
    @NamedQuery(name = "ChuTro.findBySdt", query = "SELECT c FROM ChuTro c WHERE c.sdt = :sdt")})
public class ChuTro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 60)
    @Column(name = "ho")
    private String ho;
    @Size(max = 45)
    @Column(name = "ten")
    private String ten;
    @Size(max = 100)
    @Column(name = "diaChi")
    private String diaChi;
    @Size(max = 12)
    @Column(name = "sdt")
    private String sdt;
    @Column(name = "ngayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @OneToMany(mappedBy = "idchuTro")
    @JsonIgnore
    private Set<Tin> tinSet;
    @OneToMany(mappedBy = "idchuTro")
    @JsonIgnore
    private Set<PhongTro> phongTroSet;
    @JoinColumn(name = "id_taiKhoan", referencedColumnName = "id")
    @OneToOne
    private TaiKhoan idtaiKhoan;
    @OneToMany(mappedBy = "idchuTro")
    @JsonIgnore
    private Set<Follow> followSet;

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

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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
    public Set<Tin> getTinSet() {
        return tinSet;
    }

    public void setTinSet(Set<Tin> tinSet) {
        this.tinSet = tinSet;
    }

    @XmlTransient
    public Set<PhongTro> getPhongTroSet() {
        return phongTroSet;
    }

    public void setPhongTroSet(Set<PhongTro> phongTroSet) {
        this.phongTroSet = phongTroSet;
    }

    public TaiKhoan getIdtaiKhoan() {
        return idtaiKhoan;
    }

    public void setIdtaiKhoan(TaiKhoan idtaiKhoan) {
        this.idtaiKhoan = idtaiKhoan;
    }

    @XmlTransient
    public Set<Follow> getFollowSet() {
        return followSet;
    }

    public void setFollowSet(Set<Follow> followSet) {
        this.followSet = followSet;
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

    /**
     * @return the ngayTao
     */
    public Date getNgayTao() {
        return ngayTao;
    }

    /**
     * @param ngayTao the ngayTao to set
     */
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
}
