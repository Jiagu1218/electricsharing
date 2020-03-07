package com.cyxy.dao;

import com.cyxy.annotation.MyBatisDAO;
import com.cyxy.entity.Admin;

@MyBatisDAO
public interface AdminDao {
    Admin login(String adminName, String adminPassword);
}
