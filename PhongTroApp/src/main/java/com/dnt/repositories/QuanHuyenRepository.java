/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories;

import com.dnt.pojo.QuanHuyen;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DikamonTu
 */
public interface QuanHuyenRepository {
    List<QuanHuyen> getQuanHuyen(Map<String, String> params);
    void addOrUpdate(QuanHuyen q);
    QuanHuyen getQuanHuyenById(int id);
    void deleteQuanHuyen(int id);
}
