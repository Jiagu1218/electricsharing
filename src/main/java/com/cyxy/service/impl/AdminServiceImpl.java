package com.cyxy.service.impl;

import com.cyxy.dao.AdminDao;
import com.cyxy.entity.Admin;
import com.cyxy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin login(Admin admin) {
        return adminDao.login(admin.getAdminName(),admin.getAdminPassword());
    }
}
