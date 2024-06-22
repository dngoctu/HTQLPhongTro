/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services;

import com.dnt.pojo.PhongTro;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DikamonTu
 */
public interface PhongTroService {
    List<PhongTro> getPhongTro(Map<String, String> params);
    void addOrUpdate(PhongTro p);
    PhongTro getPhongTroById(int id);
    void deletePhongTro(int id);
}
