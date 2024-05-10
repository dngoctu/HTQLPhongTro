/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author DikamonTu
 */
@Embeddable
public class CommentPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_nguoiThue")
    private int idnguoiThue;

    public CommentPK() {
    }

    public CommentPK(int id, int idnguoiThue) {
        this.id = id;
        this.idnguoiThue = idnguoiThue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdnguoiThue() {
        return idnguoiThue;
    }

    public void setIdnguoiThue(int idnguoiThue) {
        this.idnguoiThue = idnguoiThue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idnguoiThue;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentPK)) {
            return false;
        }
        CommentPK other = (CommentPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idnguoiThue != other.idnguoiThue) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dnt.pojo.CommentPK[ id=" + id + ", idnguoiThue=" + idnguoiThue + " ]";
    }
    
}
