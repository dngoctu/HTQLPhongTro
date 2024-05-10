package com.dnt.pojo;

import com.dnt.pojo.ChuTro;
import com.dnt.pojo.HinhAnhTro;
import com.dnt.pojo.KinhdoVido;
import com.dnt.pojo.QuanHuyen;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-10T20:42:10")
@StaticMetamodel(PhongTro.class)
public class PhongTro_ { 

    public static volatile SetAttribute<PhongTro, KinhdoVido> kinhdoVidoSet;
    public static volatile SingularAttribute<PhongTro, ChuTro> idchuTro;
    public static volatile SingularAttribute<PhongTro, String> diaChiPhong;
    public static volatile SingularAttribute<PhongTro, Integer> soNguoi;
    public static volatile SingularAttribute<PhongTro, QuanHuyen> idQuan;
    public static volatile SingularAttribute<PhongTro, Short> conTrong;
    public static volatile SingularAttribute<PhongTro, Integer> id;
    public static volatile SetAttribute<PhongTro, HinhAnhTro> hinhAnhTroSet;
    public static volatile SingularAttribute<PhongTro, Double> gia;

}