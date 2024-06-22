/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories;

import com.dnt.pojo.KinhdoVido;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DikamonTu
 */
public interface KinhdoVidoRepository {
    List<KinhdoVido> getKinhdoVido(Map<String, String> params);
    void addOrUpdate(KinhdoVido kv);
    KinhdoVido getKinhdoVidoById(int id);
    void deleteKinhdoVido(int id);
}
