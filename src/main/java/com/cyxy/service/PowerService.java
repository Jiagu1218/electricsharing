package com.cyxy.service;

import com.cyxy.entity.JsonResult;
import com.cyxy.entity.Power;
import com.cyxy.entity.PowerDTO;
import com.cyxy.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface PowerService {
    List<Power> findPowerByHubId(int hubId);
    Map<String,Integer> getCanRentCountAndCanReturnCount(int partnerId);
    JsonResult rentPower(User user, int partnerId);
    JsonResult returnPower(User user, int partnerId);
    PageInfo<Power> findPower(int pageSize, int pageNum, String sort, String order, Map map);
    int deletePowerByList(List<PowerDTO> list);
}
