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
@Table(name = "nguoi_thue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NguoiThue.findAll", query = "SELECT n FROM NguoiThue n"),
    @NamedQuery(name = "NguoiThue.findById", query = "SELECT n FROM NguoiThue n WHERE n.id = :id"),
    @NamedQuery(name = "NguoiThue.findByHo", query = "SELECT n FROM NguoiThue n WHERE n.ho = :ho"),
    @NamedQuery(name = "NguoiThue.findByTen", query = "SELECT n FROM NguoiThue n WHERE n.ten = :ten"),
    @NamedQuery(name = "NguoiThue.findBySdt", query = "SELECT n FROM NguoiThue n WHERE n.sdt = :sdt"),
    @NamedQuery(name = "NguoiThue.findByDiaChi", query = "SELECT n FROM NguoiThue n WHERE n.diaChi = :diaChi")})
public class NguoiThue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "ho")
    private String ho;
    @Size(max = 45)
    @Column(name = "ten")
    private String ten;
    @Size(max = 45)
    @Column(name = "sdt")
    private String sdt;
    @Size(max = 100)
    @Column(name = "diaChi")
    private String diaChi;
    @OneToMany(mappedBy = "idnguoiThue")
    private Set<Tin> tinSet;
    @OneToMany(mappedBy = "idnguoiThue")
    private Set<KinhdoVido> kinhdoVidoSet;
    @OneToMany(mappedBy = "idnguoiThue")
    private Set<Follow> followSet;
    @JoinColumn(name = "id_taiKhoan", referencedColumnName = "id")
    @OneToOne
    private TaiKhoan idtaiKhoan;

    public NguoiThue() {
    }

    public NguoiThue(Integer id) {
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @XmlTransient
    public Set<Tin> getTinSet() {
        return tinSet;
    }

    public void setTinSet(Set<Tin> tinSet) {
        this.tinSet = tinSet;
    }

    @XmlTransient
    public Set<KinhdoVido> getKinhdoVidoSet() {
        return kinhdoVidoSet;
    }

    public void setKinhdoVidoSet(Set<KinhdoVido> kinhdoVidoSet) {
        this.kinhdoVidoSet = kinhdoVidoSet;
    }

    @XmlTransient
    public Set<Follow> getFollowSet() {
        return followSet;
    }

    public void setFollowSet(Set<Follow> followSet) {
        this.followSet = followSet;
    }

    public TaiKhoan getIdtaiKhoan() {
        return idtaiKhoan;
    }

    public void setIdtaiKhoan(TaiKhoan idtaiKhoan) {
        this.idtaiKhoan = idtaiKhoan;
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
        if (!(object instanceof NguoiThue)) {
            return false;
        }
        NguoiThue other = (NguoiThue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dnt.pojo.NguoiThue[ id=" + id + " ]";
    }
    
}
