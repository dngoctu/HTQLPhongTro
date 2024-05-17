package com.dnt.pojo;

import com.dnt.pojo.PhongTro;
import com.dnt.pojo.ThanhPho;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-17T20:19:17")
@StaticMetamodel(QuanHuyen.class)
public class QuanHuyen_ { 

    public static volatile SingularAttribute<QuanHuyen, ThanhPho> idthanhPho;
    public static volatile SetAttribute<QuanHuyen, PhongTro> phongTroSet;
    public static volatile SingularAttribute<QuanHuyen, Integer> id;
    public static volatile SingularAttribute<QuanHuyen, String> ten;

}