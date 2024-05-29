/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories;

import java.util.List;
import java.util.Map;

/**
 *
 * @author DikamonTu
 */
public interface StatsRepository {
    Object statsByNguoiThue();
    Object statsByChuTro();
    List<Object[]> statsNguoiThueByPeriod(int time, String period);
    List<Object[]> statsChuTroByPeriod(int time, String period);

}
