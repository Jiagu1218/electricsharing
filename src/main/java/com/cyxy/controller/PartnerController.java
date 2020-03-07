package com.cyxy.controller;

import com.cyxy.entity.JsonResult;
import com.cyxy.entity.Partner;
import com.cyxy.enums.ResultEnum;
import com.cyxy.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/partner")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @GetMapping(value = "/findPartner.do")
    public JsonResult<Partner> findPartner(HttpServletRequest request, Partner partner){
        return new JsonResult<Partner>(partnerService.findPartner(partner));
    }

    @PostMapping(value = "/addPartner.do")
    public JsonResult<Boolean> addPartner(HttpServletRequest request,@RequestBody Partner partner){
        if (partnerService.addPartner(partner)){
            return new JsonResult<Boolean>("添加成功");
        }else{
            return new JsonResult<Boolean>("添加失败");
        }
    }

    @PostMapping("/updatePartner.do")
    public JsonResult updatePartner(Partner partner){
        if (partnerService.update(partner) == 1){
            return new JsonResult(ResultEnum.UPDATE_PARTNER_SUCCESS.getCode(),ResultEnum.UPDATE_PARTNER_SUCCESS.getMsg());
        }else{
            return new JsonResult(ResultEnum.UPDATE_PARTNER_FIELD.getCode(),ResultEnum.UPDATE_PARTNER_FIELD.getMsg());
        }
    }
}
