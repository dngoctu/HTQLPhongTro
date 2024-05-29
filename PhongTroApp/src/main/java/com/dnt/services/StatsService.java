/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services;

import java.util.List;

/**
 *
 * @author DikamonTu
 */
public interface StatsService {
    Object statsByNguoiThue();
    Object statsByChuTro();
    List<Object[]> statsNguoiThueByPeriod(int time, String period);
    List<Object[]> statsChuTroByPeriod(int time, String period);
}
