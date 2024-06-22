/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services;

import com.dnt.pojo.HinhAnhTro;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DikamonTu
 */
public interface HinhAnhTroService {
    List<HinhAnhTro> getHinhAnhTro(Map<String, String> params);
    void addOrUpdate(HinhAnhTro hinhanh);
    HinhAnhTro getHinhAnhTroById(int id);
    void deleteHinhAnhTro(int id);
}
