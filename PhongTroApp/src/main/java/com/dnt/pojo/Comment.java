/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DikamonTu
 */
@Entity
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id"),
    @NamedQuery(name = "Comment.findByThoiGian", query = "SELECT c FROM Comment c WHERE c.thoiGian = :thoiGian")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "noiDung")
    private String noiDung;
    @Column(name = "thoiGian")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGian;
    @OneToMany(mappedBy = "idComment")
    private Set<Tin> tinSet;
    @JoinColumn(name = "id_chuTro", referencedColumnName = "id")
    @ManyToOne
    private ChuTro idchuTro;
    @JoinColumn(name = "id_nguoiThue", referencedColumnName = "id")
    @ManyToOne
    private NguoiThue idnguoiThue;

    public Comment() {
    }

    public Comment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    @XmlTransient
    public Set<Tin> getTinSet() {
        return tinSet;
    }

    public void setTinSet(Set<Tin> tinSet) {
        this.tinSet = tinSet;
    }

    public ChuTro getIdchuTro() {
        return idchuTro;
    }

    public void setIdchuTro(ChuTro idchuTro) {
        this.idchuTro = idchuTro;
    }

    public NguoiThue getIdnguoiThue() {
        return idnguoiThue;
    }

    public void setIdnguoiThue(NguoiThue idnguoiThue) {
        this.idnguoiThue = idnguoiThue;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dnt.pojo.Comment[ id=" + id + " ]";
    }
    
}
