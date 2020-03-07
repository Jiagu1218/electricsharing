package com.cyxy.dao;

import com.cyxy.annotation.MyBatisDAO;
import com.cyxy.entity.User;

import java.util.List;
import java.util.Map;

@MyBatisDAO
public interface UserDao {
    List<User> findUser(Map map);
    int updateUserById(User user);
    User userLogin(User user);
    User findUserByUserName(String userName);
    User findUserByUserPhone(String userPhone);
    int userRegister(User user);
    int updateUser(User user);
    User findUserById(int userId);
    int updateUserBalance(int userId, double price);
    int deleteUsers(List<Integer> list);
    void charge(Integer userId, double amount);
}
