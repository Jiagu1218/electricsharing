package com.cyxy.dao;

import com.cyxy.annotation.MyBatisDAO;
import com.cyxy.entity.Hub;

import java.util.List;

@MyBatisDAO
public interface HubDao {
    List<Hub> findHubByPartnerId(int partnerId);
    List<Hub> findCanUserReturnHub(int partnerId);
    int addHub(Hub hub);
}
