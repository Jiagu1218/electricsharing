package com.cyxy.controller;

import com.cyxy.entity.JsonResult;
import com.cyxy.entity.OrderDTO;
import com.cyxy.entity.User;
import com.cyxy.enums.ResultEnum;
import com.cyxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findUsers.do")
    public JsonResult<User> findUsers(int pageSize, int pageNum, String sort, String order, String userId, String userName, String status){
        Map<String,Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("userName",userName);
        param.put("userState",status);
        return new JsonResult<User>(userService.findUser(pageSize,pageNum,sort,order,param));
    }
    @GetMapping("/getLoginUser.do")
    public JsonResult<User> getSessionUser(HttpServletRequest request){
        return new JsonResult<User>((User)request.getSession().getAttribute("user"));
    }

    @PostMapping("/userLogin.do")
    public void userLogin(HttpServletRequest request, User user , HttpServletResponse response) throws IOException, ServletException {
        User loginUser =userService.userLogin(user);
        if (loginUser != null && "正常".equals(loginUser.getUserState())){
            request.getSession().setAttribute("user",loginUser);
            request.getRequestDispatcher("/index.do").forward(request,response);
        }else if(loginUser != null && "禁用".equals(loginUser.getUserState())){
            request.setAttribute("msg", "该账号已被禁用");
            request.getRequestDispatcher("/loginandregister.do").forward(request,response);
        }else{
            request.setAttribute("msg", "登录失败");
            request.getRequestDispatcher("/loginandregister.do").forward(request,response);
        }
    }

    @DeleteMapping("/deleteUsers.do")
    public JsonResult deleteOrders(HttpServletRequest request,@RequestBody List<User> users){
        if (userService.deleteUsers(users)==users.size()){
            return new JsonResult<>("删除成功");
        }else{
            return new JsonResult<>("删除失败");
        }
    }

    @GetMapping("/exit.do")
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute("user");
        request.getRequestDispatcher("/").forward(request,response);
    }

    @GetMapping("/checkUserName.do")
    public JsonResult checkUserName(String userName){
        User user = userService.findUserByUserName(userName);
        if (user != null){
            return new JsonResult(ResultEnum.EXIST_USER_NAME.getCode(),ResultEnum.EXIST_USER_NAME.getMsg());
        }
        return new JsonResult(ResultEnum.LEGAL_USER_NAME.getCode(),ResultEnum.LEGAL_USER_NAME.getMsg());
    }

    @GetMapping("/checkUserPhone.do")
    public JsonResult checkUserPhone(String userPhone){
        if (!userService.checkPhone(userPhone)){
            return new JsonResult(ResultEnum.ILLEGAL_PHONE.getCode(),ResultEnum.ILLEGAL_PHONE.getMsg());
        }
        User user = userService.findUserByUserPhone(userPhone);
        if (user != null){
            return new JsonResult(ResultEnum.EXIST_USER_PHONE.getCode(),ResultEnum.EXIST_USER_PHONE.getMsg());
        }
        return new JsonResult(ResultEnum.LEGAL_USER_PHONE.getCode(),ResultEnum.LEGAL_USER_PHONE.getMsg());
    }

    @PostMapping("/userRegister.do")
    public JsonResult userRegister(HttpServletRequest request, User user,HttpServletResponse response) throws IOException, ServletException{
        if (userService.userRegister(user) == 1){
            request.setAttribute("regMsg",ResultEnum.REGISTER_SUCCESS.getMsg());
            request.getRequestDispatcher("/loginandregister.do").forward(request,response);
            return new JsonResult(ResultEnum.REGISTER_SUCCESS.getCode(),ResultEnum.REGISTER_SUCCESS.getMsg());
        }else {
            return new JsonResult(ResultEnum.REGISTER_FIELD.getCode(),ResultEnum.REGISTER_FIELD.getMsg());
        }
    }

    @GetMapping("/sendCode.do")
    public JsonResult sendSMSCode(HttpServletRequest request, String userPhone){
        return userService.sendSMSCode(request,userPhone);
    }

    @PostMapping("/checkSmsCode.do")
    public JsonResult checkSmsCode(HttpServletRequest request, String smsCode){
        String sessionCode = (String) request.getSession().getAttribute("SMSCode");
        if (smsCode.equals(sessionCode)){
            return new JsonResult(ResultEnum.CHECK_SMS_CODE_SUCCESS.getCode(),ResultEnum.CHECK_SMS_CODE_SUCCESS.getMsg());
        }else{
            return new JsonResult(ResultEnum.CHECK_SMS_CODE_FIELD.getCode(),ResultEnum.CHECK_SMS_CODE_FIELD.getMsg());
        }
    }

    @PostMapping("/updateUser.do")
    public JsonResult updateUser(User user){
        if (userService.updateUser(user) == 1){
            return new JsonResult(ResultEnum.UPDATE_USER_INFO_SUCCESS.getCode(),ResultEnum.UPDATE_USER_INFO_SUCCESS.getMsg());
        }else {
            return new JsonResult(ResultEnum.UPDATE_USER_INFO_FIELD.getCode(),ResultEnum.UPDATE_USER_INFO_FIELD.getMsg());
        }
    }

    @GetMapping("/getPersonalInfo.do")
    public JsonResult<User> getPersonalInfo(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        return new JsonResult<>(userService.findUserByUserId(user.getUserId()));
    }

    @GetMapping("/charge.do")
    public void charge(HttpServletRequest request, Double amount, HttpServletResponse response)throws IOException{
        User user=(User) request.getSession().getAttribute("user");
        Integer userId=user.getUserId();
        userService.charge(userId, amount);
        response.sendRedirect("info.do");
    }
}
