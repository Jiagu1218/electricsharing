package com.cyxy.controller;

import com.cyxy.entity.Hub;
import com.cyxy.entity.JsonResult;
import com.cyxy.service.HubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/hub")
public class HubController {

    @Autowired
    private HubService hubService;

    @GetMapping("/findHubById.do")
    public JsonResult<Hub> findHubById(HttpServletRequest request, Integer partnerId){
        return new JsonResult<Hub>(hubService.findHubByPartnerId(partnerId));
    }

    @PostMapping("/addHub.do")
    public JsonResult addHub(HttpServletRequest request, int partnerId, int hubPort){
        return hubService.addHub(partnerId, hubPort);
    }
}
