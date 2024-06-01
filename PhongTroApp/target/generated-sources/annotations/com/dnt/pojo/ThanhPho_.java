package com.dnt.pojo;

import com.dnt.pojo.QuanHuyen;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-31T09:10:17")
@StaticMetamodel(ThanhPho.class)
public class ThanhPho_ { 

    public static volatile SingularAttribute<ThanhPho, Integer> id;
    public static volatile SetAttribute<ThanhPho, QuanHuyen> quanHuyenSet;
    public static volatile SingularAttribute<ThanhPho, String> ten;

}