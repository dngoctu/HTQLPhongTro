package com.dnt.pojo;

import com.dnt.pojo.Admin;
import com.dnt.pojo.ChuTro;
import com.dnt.pojo.Comment;
import com.dnt.pojo.NguoiThue;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-22T08:41:55")
@StaticMetamodel(TaiKhoan.class)
public class TaiKhoan_ { 

    public static volatile SingularAttribute<TaiKhoan, String> vaiTro;
    public static volatile SingularAttribute<TaiKhoan, String> password;
    public static volatile SetAttribute<TaiKhoan, Comment> commentSet;
    public static volatile SingularAttribute<TaiKhoan, Admin> admin;
    public static volatile SingularAttribute<TaiKhoan, NguoiThue> nguoiThue;
    public static volatile SingularAttribute<TaiKhoan, Integer> id;
    public static volatile SingularAttribute<TaiKhoan, String> avatar;
    public static volatile SingularAttribute<TaiKhoan, String> email;
    public static volatile SingularAttribute<TaiKhoan, ChuTro> chuTro;
    public static volatile SingularAttribute<TaiKhoan, String> username;

}