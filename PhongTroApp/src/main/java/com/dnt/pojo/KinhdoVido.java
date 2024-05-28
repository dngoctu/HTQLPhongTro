/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.pojo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DikamonTu
 */
@Entity
@Table(name = "kinhdo_vido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KinhdoVido.findAll", query = "SELECT k FROM KinhdoVido k"),
    @NamedQuery(name = "KinhdoVido.findById", query = "SELECT k FROM KinhdoVido k WHERE k.id = :id"),
    @NamedQuery(name = "KinhdoVido.findByKinhDo", query = "SELECT k FROM KinhdoVido k WHERE k.kinhDo = :kinhDo"),
    @NamedQuery(name = "KinhdoVido.findByViDo", query = "SELECT k FROM KinhdoVido k WHERE k.viDo = :viDo")})
public class KinhdoVido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "kinhDo")
    private String kinhDo;
    @Size(max = 45)
    @Column(name = "viDo")
    private String viDo;
    @JoinColumn(name = "id_nguoiThue", referencedColumnName = "id")
    @ManyToOne
    private NguoiThue idnguoiThue;
    @JoinColumn(name = "id_phongTro", referencedColumnName = "id")
    @ManyToOne
    private PhongTro idphongTro;

    public KinhdoVido() {
    }

    public KinhdoVido(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKinhDo() {
        return kinhDo;
    }

    public void setKinhDo(String kinhDo) {
        this.kinhDo = kinhDo;
    }

    public String getViDo() {
        return viDo;
    }

    public void setViDo(String viDo) {
        this.viDo = viDo;
    }

    public NguoiThue getIdnguoiThue() {
        return idnguoiThue;
    }

    public void setIdnguoiThue(NguoiThue idnguoiThue) {
        this.idnguoiThue = idnguoiThue;
    }

    public PhongTro getIdphongTro() {
        return idphongTro;
    }

    public void setIdphongTro(PhongTro idphongTro) {
        this.idphongTro = idphongTro;
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
        if (!(object instanceof KinhdoVido)) {
            return false;
        }
        KinhdoVido other = (KinhdoVido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dnt.pojo.KinhdoVido[ id=" + id + " ]";
    }
    
}
