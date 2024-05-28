/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.pojo;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "phong_tro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhongTro.findAll", query = "SELECT p FROM PhongTro p"),
    @NamedQuery(name = "PhongTro.findById", query = "SELECT p FROM PhongTro p WHERE p.id = :id"),
    @NamedQuery(name = "PhongTro.findByDiaChiPhong", query = "SELECT p FROM PhongTro p WHERE p.diaChiPhong = :diaChiPhong"),
    @NamedQuery(name = "PhongTro.findByGia", query = "SELECT p FROM PhongTro p WHERE p.gia = :gia"),
    @NamedQuery(name = "PhongTro.findBySoNguoi", query = "SELECT p FROM PhongTro p WHERE p.soNguoi = :soNguoi"),
    @NamedQuery(name = "PhongTro.findByConTrong", query = "SELECT p FROM PhongTro p WHERE p.conTrong = :conTrong"),
    @NamedQuery(name = "PhongTro.findByNgayDang", query = "SELECT p FROM PhongTro p WHERE p.ngayDang = :ngayDang"),
    @NamedQuery(name = "PhongTro.findByNgayCapNhat", query = "SELECT p FROM PhongTro p WHERE p.ngayCapNhat = :ngayCapNhat")})
public class PhongTro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "diaChiPhong")
    private String diaChiPhong;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "gia")
    private Double gia;
    @Column(name = "soNguoi")
    private Integer soNguoi;
    @Column(name = "conTrong")
    private Short conTrong;
    @Column(name = "ngayDang")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayDang;
    @Column(name = "ngayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;
    @OneToMany(mappedBy = "idphongTro")
    @JsonIgnore
    private Set<HinhAnhTro> hinhAnhTroSet;
    @OneToMany(mappedBy = "idphongTro")
    @JsonIgnore
    private Set<Tin> tinSet;
    @OneToMany(mappedBy = "idphongTro")
    @JsonIgnore
    private Set<KinhdoVido> kinhdoVidoSet;
    @JoinColumn(name = "id_chuTro", referencedColumnName = "id")
    @ManyToOne
    private ChuTro idchuTro;
    @JoinColumn(name = "id_quan", referencedColumnName = "id")
    @ManyToOne
    private QuanHuyen idQuan;

    public PhongTro() {
    }

    public PhongTro(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiaChiPhong() {
        return diaChiPhong;
    }

    public void setDiaChiPhong(String diaChiPhong) {
        this.diaChiPhong = diaChiPhong;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Integer getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(Integer soNguoi) {
        this.soNguoi = soNguoi;
    }

    public Short getConTrong() {
        return conTrong;
    }

    public void setConTrong(Short conTrong) {
        this.conTrong = conTrong;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    @XmlTransient
    public Set<HinhAnhTro> getHinhAnhTroSet() {
        return hinhAnhTroSet;
    }

    public void setHinhAnhTroSet(Set<HinhAnhTro> hinhAnhTroSet) {
        this.hinhAnhTroSet = hinhAnhTroSet;
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

    public ChuTro getIdchuTro() {
        return idchuTro;
    }

    public void setIdchuTro(ChuTro idchuTro) {
        this.idchuTro = idchuTro;
    }

    public QuanHuyen getIdQuan() {
        return idQuan;
    }

    public void setIdQuan(QuanHuyen idQuan) {
        this.idQuan = idQuan;
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
        if (!(object instanceof PhongTro)) {
            return false;
        }
        PhongTro other = (PhongTro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dnt.pojo.PhongTro[ id=" + id + " ]";
    }
    
}
