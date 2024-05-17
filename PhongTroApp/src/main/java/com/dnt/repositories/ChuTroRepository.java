/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories;

import com.dnt.pojo.ChuTro;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DikamonTu
 */
public interface ChuTroRepository {
    List<ChuTro> getChuTro(Map<String, String> params);
    void addOrUpdateChuTro(ChuTro c);
    ChuTro getChuTroById(int id);
    void deleteChuTro(int id);
}
