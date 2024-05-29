/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.dnt.repositories.StatsRepository;
import com.dnt.services.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DikamonTu
 */
@Service
public class StatsServiceImpl implements StatsService{
    @Autowired
    private StatsRepository statsRepo;

    @Override
    public Object statsByNguoiThue() {
       return this.statsRepo.statsByNguoiThue();
    }

    @Override
    public Object statsByChuTro() {
       return this.statsRepo.statsByChuTro();
}

    @Override
    public List<Object[]> statsNguoiThueByPeriod(int time, String period) {
        return this.statsRepo.statsNguoiThueByPeriod(time, period);
    }

    @Override
    public List<Object[]> statsChuTroByPeriod(int time, String period) {
        return this.statsRepo.statsChuTroByPeriod(time, period);
    }

    
    
}
