/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.commentPK.id = :id"),
    @NamedQuery(name = "Comment.findByThoiGian", query = "SELECT c FROM Comment c WHERE c.thoiGian = :thoiGian"),
    @NamedQuery(name = "Comment.findByIdnguoiThue", query = "SELECT c FROM Comment c WHERE c.commentPK.idnguoiThue = :idnguoiThue")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommentPK commentPK;
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
    @JoinColumn(name = "id_nguoiThue", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NguoiThue nguoiThue;

    public Comment() {
    }

    public Comment(CommentPK commentPK) {
        this.commentPK = commentPK;
    }

    public Comment(int id, int idnguoiThue) {
        this.commentPK = new CommentPK(id, idnguoiThue);
    }

    public CommentPK getCommentPK() {
        return commentPK;
    }

    public void setCommentPK(CommentPK commentPK) {
        this.commentPK = commentPK;
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

    public NguoiThue getNguoiThue() {
        return nguoiThue;
    }

    public void setNguoiThue(NguoiThue nguoiThue) {
        this.nguoiThue = nguoiThue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentPK != null ? commentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentPK == null && other.commentPK != null) || (this.commentPK != null && !this.commentPK.equals(other.commentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dnt.pojo.Comment[ commentPK=" + commentPK + " ]";
    }
    
}
