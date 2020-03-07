package com.cyxy.service;

import com.cyxy.entity.Hub;
import com.cyxy.entity.JsonResult;

import java.util.List;

public interface HubService {
    List<Hub> findHubByPartnerId(int hubId);
    JsonResult addHub(int partnerId, int hubPort);
}
