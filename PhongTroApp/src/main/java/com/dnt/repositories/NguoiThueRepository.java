/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories;

import com.dnt.pojo.NguoiThue;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DikamonTu
 */
public interface NguoiThueRepository {
    List<NguoiThue> getNguoiThue(Map<String, String> params);
    void addOrUpdateNguoiThue(NguoiThue n);
    NguoiThue getNguoiThueById(int id);
    void deleteNguoiThue(int id);
}
