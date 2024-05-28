package com.dnt.pojo;

import com.dnt.pojo.TaiKhoan;
import com.dnt.pojo.Tin;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-28T10:10:40")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, TaiKhoan> idtaiKhoan;
    public static volatile SetAttribute<Comment, Tin> tinSet;
    public static volatile SingularAttribute<Comment, Integer> id;
    public static volatile SingularAttribute<Comment, String> noiDung;
    public static volatile SingularAttribute<Comment, Date> thoiGian;

}