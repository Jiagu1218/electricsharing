package com.cyxy.service;

import com.cyxy.entity.Partner;

import java.util.List;

public interface PartnerService {
    List<Partner> findPartner(Partner partner);
    boolean addPartner(Partner partner);
    int update(Partner partner);
}
