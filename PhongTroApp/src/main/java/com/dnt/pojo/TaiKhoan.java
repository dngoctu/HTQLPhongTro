/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DikamonTu
 */
@Entity
@Table(name = "tai_khoan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaiKhoan.findAll", query = "SELECT t FROM TaiKhoan t"),
    @NamedQuery(name = "TaiKhoan.findById", query = "SELECT t FROM TaiKhoan t WHERE t.id = :id"),
    @NamedQuery(name = "TaiKhoan.findByUsername", query = "SELECT t FROM TaiKhoan t WHERE t.username = :username"),
    @NamedQuery(name = "TaiKhoan.findByPassword", query = "SELECT t FROM TaiKhoan t WHERE t.password = :password"),
    @NamedQuery(name = "TaiKhoan.findByAvatar", query = "SELECT t FROM TaiKhoan t WHERE t.avatar = :avatar"),
    @NamedQuery(name = "TaiKhoan.findByVaiTro", query = "SELECT t FROM TaiKhoan t WHERE t.vaiTro = :vaiTro")})
public class TaiKhoan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "avatar")
    private String avatar;
    @Size(max = 45)
    @Column(name = "vaiTro")
    private String vaiTro;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "taiKhoan")
    private Admin admin;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "taiKhoan")
    private ChuTro chuTro;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "taiKhoan")
    private NguoiThue nguoiThue;

    public TaiKhoan() {
    }

    public TaiKhoan(Integer id) {
        this.id = id;
    }

    public TaiKhoan(Integer id, String username, String password, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ChuTro getChuTro() {
        return chuTro;
    }

    public void setChuTro(ChuTro chuTro) {
        this.chuTro = chuTro;
    }

    public NguoiThue getNguoiThue() {
        return nguoiThue;
    }

    public void setNguoiThue(NguoiThue nguoiThue) {
        this.nguoiThue = nguoiThue;
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
        if (!(object instanceof TaiKhoan)) {
            return false;
        }
        TaiKhoan other = (TaiKhoan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dnt.pojo.TaiKhoan[ id=" + id + " ]";
    }
    
}
