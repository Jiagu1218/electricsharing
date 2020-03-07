package com.cyxy.controller;

import com.cyxy.entity.Admin;
import com.cyxy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
//@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/adminLogin.do")
    public void login(Admin admin, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Admin sessionAdmin = adminService.login(admin);
        if(sessionAdmin!=null){
            sessionAdmin.setAdminPassword(null);
            request.getSession().setAttribute("admin",sessionAdmin);
            request.removeAttribute("msg");
            request.getRequestDispatcher("/partner.do").forward(request,response);
        }else{
            request.setAttribute("msg", "登录失败");
            request.getRequestDispatcher("/login.do").forward(request,response);
        }
    }

    @RequestMapping("/adminLogout.do")
    public void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("admin");
        request.setAttribute("msg", "您已安全退出本系统！~");
        request.getRequestDispatcher("/login.do").forward(request,response);
    }
}
