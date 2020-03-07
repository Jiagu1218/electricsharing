package com.cyxy.service;

import com.cyxy.entity.JsonResult;
import com.cyxy.entity.User;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    PageInfo<User> findUser(int pageSize, int pageNum, String sort, String order, Map map);
    User userLogin(User user);
    User findUserByUserName(String userName);
    boolean checkPhone(String userPhone);
    User findUserByUserPhone(String userPhone);
    int userRegister(User user);
    JsonResult sendSMSCode(HttpServletRequest request, String userPhone);
    int updateUser(User user);
    User findUserByUserId(int userId);
    int deleteUsers(List<User> list);
    void charge(Integer userId, Double amount);
}
