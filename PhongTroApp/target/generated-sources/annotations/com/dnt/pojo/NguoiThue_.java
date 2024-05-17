package com.dnt.pojo;

import com.dnt.pojo.Follow;
import com.dnt.pojo.KinhdoVido;
import com.dnt.pojo.TaiKhoan;
import com.dnt.pojo.Tin;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-17T20:19:17")
@StaticMetamodel(NguoiThue.class)
public class NguoiThue_ { 

    public static volatile SingularAttribute<NguoiThue, TaiKhoan> idtaiKhoan;
    public static volatile SingularAttribute<NguoiThue, String> diaChi;
    public static volatile SingularAttribute<NguoiThue, String> sdt;
    public static volatile SetAttribute<NguoiThue, KinhdoVido> kinhdoVidoSet;
    public static volatile SetAttribute<NguoiThue, Tin> tinSet;
    public static volatile SingularAttribute<NguoiThue, String> ho;
    public static volatile SetAttribute<NguoiThue, Follow> followSet;
    public static volatile SingularAttribute<NguoiThue, Integer> id;
    public static volatile SingularAttribute<NguoiThue, String> ten;

}