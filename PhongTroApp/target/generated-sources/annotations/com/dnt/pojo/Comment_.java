package com.dnt.pojo;

import com.dnt.pojo.ChuTro;
import com.dnt.pojo.CommentPK;
import com.dnt.pojo.NguoiThue;
import com.dnt.pojo.Tin;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-10T20:42:10")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SetAttribute<Comment, Tin> tinSet;
    public static volatile SingularAttribute<Comment, ChuTro> idchuTro;
    public static volatile SingularAttribute<Comment, CommentPK> commentPK;
    public static volatile SingularAttribute<Comment, NguoiThue> nguoiThue;
    public static volatile SingularAttribute<Comment, String> noiDung;
    public static volatile SingularAttribute<Comment, Date> thoiGian;

}