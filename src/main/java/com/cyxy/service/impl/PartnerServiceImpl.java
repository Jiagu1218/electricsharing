package com.cyxy.service.impl;

import com.cyxy.dao.PartnerDao;
import com.cyxy.entity.Partner;
import com.cyxy.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerDao partnerDao;
    @Override
    public List<Partner> findPartner(Partner partner) {
        return partnerDao.findPartner(partner);
    }

    @Override
    public boolean addPartner(Partner partner) {
        partnerDao.addPartner(partner);
        return true;
    }

    @Override
    public int update(Partner partner) {
        return partnerDao.updatePartner(partner);
    }
}
