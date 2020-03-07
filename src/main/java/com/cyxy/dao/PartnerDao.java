package com.cyxy.dao;

import com.cyxy.annotation.MyBatisDAO;
import com.cyxy.entity.Partner;

import java.util.List;
@MyBatisDAO
public interface PartnerDao {
    List<Partner> findPartner(Partner partner);
    void addPartner(Partner partner);
    Partner getPartnerByPartnerId(int partnerId);
    int updatePartner(Partner partner);
}
