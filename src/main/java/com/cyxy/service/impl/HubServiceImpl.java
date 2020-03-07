package com.cyxy.service.impl;

import com.cyxy.dao.HubDao;
import com.cyxy.dao.PartnerDao;
import com.cyxy.dao.PowerDao;
import com.cyxy.entity.Hub;
import com.cyxy.entity.JsonResult;
import com.cyxy.entity.Partner;
import com.cyxy.entity.Power;
import com.cyxy.service.HubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HubServiceImpl implements HubService {

    @Autowired
    private HubDao hubDao;
    @Autowired
    private PartnerDao partnerDao;
    @Autowired
    private PowerDao powerDao;
    @Override
    public List<Hub> findHubByPartnerId(int partnerId) {
        return hubDao.findHubByPartnerId(partnerId);
    }

    @Override
    public JsonResult addHub(int partnerId, int hubPort) {
        Partner partner = partnerDao.getPartnerByPartnerId(partnerId);
        Hub hub = new Hub();
        hub.setHubPort(hubPort);
        hub.setHubState("正常");
        hub.setPartnerId(partnerId);
        hub.setHubLng(partner.getPartnerLng());
        hub.setHubLat(partner.getPartnerLat());
        hubDao.addHub(hub);
        List<Power> powers = new ArrayList<>();
        Power power = new Power();
        power.setPowerFull(true);
        power.setOrderReturnTime(new Date());
        power.setHubId(hub.getHubId());
        for (int i = 0; i < hubPort; i++){
            powers.add(power);
        }
        powerDao.addPowerList(powers);
        return new JsonResult("添加成功");
    }
}
