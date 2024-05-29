package com.dnt.pojo;

import com.dnt.pojo.Follow;
import com.dnt.pojo.PhongTro;
import com.dnt.pojo.TaiKhoan;
import com.dnt.pojo.Tin;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-28T19:23:38")
@StaticMetamodel(ChuTro.class)
public class ChuTro_ { 

    public static volatile SingularAttribute<ChuTro, TaiKhoan> idtaiKhoan;
    public static volatile SingularAttribute<ChuTro, String> diaChi;
    public static volatile SingularAttribute<ChuTro, String> sdt;
    public static volatile SetAttribute<ChuTro, Tin> tinSet;
    public static volatile SetAttribute<ChuTro, PhongTro> phongTroSet;
    public static volatile SingularAttribute<ChuTro, String> ho;
    public static volatile SetAttribute<ChuTro, Follow> followSet;
    public static volatile SingularAttribute<ChuTro, Integer> id;
    public static volatile SingularAttribute<ChuTro, String> ten;
    public static volatile SingularAttribute<ChuTro, Date> ngayTao;

}