package com.dnt.pojo;

import com.dnt.pojo.ChuTro;
import com.dnt.pojo.Comment;
import com.dnt.pojo.NguoiThue;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-10T20:42:10")
@StaticMetamodel(Tin.class)
public class Tin_ { 

    public static volatile SingularAttribute<Tin, Comment> idComment;
    public static volatile SingularAttribute<Tin, ChuTro> idchuTro;
    public static volatile SingularAttribute<Tin, Integer> id;
    public static volatile SingularAttribute<Tin, String> loaiTin;
    public static volatile SingularAttribute<Tin, String> noiDung;
    public static volatile SingularAttribute<Tin, Date> thoiGian;
    public static volatile SingularAttribute<Tin, NguoiThue> idnguoiThue;

}