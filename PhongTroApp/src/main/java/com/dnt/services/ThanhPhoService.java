/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services;

import com.dnt.pojo.ThanhPho;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DikamonTu
 */
public interface ThanhPhoService {
    List<ThanhPho> getThanhPho(Map<String, String> params);
    void addOrUpdate(ThanhPho tp);
    ThanhPho getThanhPhoById(int id);
    void deleteThanhPho(int id);
}
