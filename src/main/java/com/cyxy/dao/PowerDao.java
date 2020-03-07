package com.cyxy.dao;

import com.cyxy.annotation.MyBatisDAO;
import com.cyxy.entity.Power;

import java.util.List;
import java.util.Map;

@MyBatisDAO
public interface PowerDao {
    List<Power> findPowerByHubId(int hubId);
    int canReturnPowerCount(int partnerId);
    int canRentPowerCount(int partnerId);
    List<Power> cnaRentPowerList(int partnerId);
    int updatePowerState(Power power);
    int updatePowerFullState();
    int addPowerList(List<Power> list);
    List<Power> findPower(Map map);
    int deletePowers(List<Integer> list);
}
