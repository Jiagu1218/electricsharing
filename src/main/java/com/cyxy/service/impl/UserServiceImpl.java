package com.cyxy.service.impl;

import com.cyxy.config.SmsConfig;
import com.cyxy.dao.UserDao;
import com.cyxy.entity.JsonResult;
import com.cyxy.entity.User;
import com.cyxy.enums.ResultEnum;
import com.cyxy.service.UserService;
import com.cyxy.utils.HttpUtils;
import com.cyxy.utils.MD5;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public PageInfo<User> findUser(int pageSize, int pageNum, String sort, String order, Map map) {
        PageHelper.startPage(pageNum, pageSize, sort+"	"+order);
        return new PageInfo<>(userDao.findUser(map));
    }

    @Override
    public User userLogin(User user) {
        String password = user.getUserPassword();
        try {
            user.setUserPassword(MD5.EncoderByMd5(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userDao.userLogin(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        if (!StringUtils.isEmpty(userName)){
            return userDao.findUserByUserName(userName);
        }
        return new User();
    }

    @Override
    public User findUserByUserPhone(String userPhone) {
        if (!StringUtils.isEmpty(userPhone)){
            return userDao.findUserByUserPhone(userPhone);
        }
        return new User();
    }

    @Override
    public int userRegister(User user) {
        if (checkUser(user)){
            try {
                String password = MD5.EncoderByMd5(user.getUserPassword());
                user.setUserPassword(password);
                user.setUserBalance(5d);
                user.setUserDeposit("是");
                return userDao.userRegister(user);
            }catch (Exception e){
                e.printStackTrace();
                return 0;
            }
        }else {
            return 0;
        }
    }

    @Override
    public JsonResult sendSMSCode(HttpServletRequest request, String userPhone) {
        if (!checkPhone(userPhone)){
            return new JsonResult(ResultEnum.ILLEGAL_PHONE.getCode(),ResultEnum.ILLEGAL_PHONE.getMsg());
        }
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + SmsConfig.appcode);
        Map<String, String> querys = new HashMap<>();
        String code = RandomStringUtils.randomNumeric(6);
        querys.put("mobile", userPhone);
        querys.put("param", "code:"+code);
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<>();
        try {
            HttpResponse response = HttpUtils.doPost(SmsConfig.host, SmsConfig.path, SmsConfig.method, headers, querys, bodys);
            System.out.println(code);
            request.getSession().setAttribute("SMSCode",code);
            return new JsonResult(ResultEnum.SEND_CODE_SUCCESS.getCode(),ResultEnum.SEND_CODE_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultEnum.SEND_CODE_FIELD.getCode(),ResultEnum.SEND_CODE_FIELD.getMsg());
        }
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User findUserByUserId(int userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public int deleteUsers(List<User> list) {
        if (list == null || list.size() == 0){
            return 0;
        }
        List<Integer> userIds = new ArrayList<>();
        for (User user: list){
            userIds.add(user.getUserId());
        }
        return userDao.deleteUsers(userIds);
    }

    @Override
    public void charge(Integer userId, Double amount) {
        userDao.charge(userId, amount);
    }

    /**
     * 校验手机号
     *
     * @param userPhone
     * @return 校验通过返回true，否则返回false
     */
    @Override
    public boolean checkPhone(String userPhone){
        String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        if (Pattern.matches(REGEX_MOBILE, userPhone)){
            return true;
        }
        return false;
    }

    private boolean checkUser(User user){
        if (user ==null){
            return false;
        } else if (StringUtils.isEmpty(user.getUserName())){
            return false;
        } else if (StringUtils.isEmpty(user.getUserPhone())){
            return false;
        }else if (StringUtils.isEmpty(user.getUserPassword())){
            return false;
        }else if ((findUserByUserName(user.getUserName())) != null &&
                (findUserByUserPhone(user.getUserPhone()) != null)){
            return false;
        }
        return true;
    }
}
