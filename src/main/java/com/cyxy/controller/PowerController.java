package com.cyxy.controller;

import com.cyxy.entity.JsonResult;
import com.cyxy.entity.Power;
import com.cyxy.entity.PowerDTO;
import com.cyxy.entity.User;
import com.cyxy.enums.ResultEnum;
import com.cyxy.service.PowerService;
import com.cyxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/power")
public class PowerController {
    @Autowired
    private PowerService powerService;
    @Autowired
    private UserService userService;
    @PostMapping("/findPower.do")
    public JsonResult<Power> findPowerByHubId(HttpServletRequest request,Integer hubId){
        return new JsonResult<>(powerService.findPowerByHubId(hubId));
    }
    @GetMapping("/getCanRentCountAndCanReturnCount.do")
    public JsonResult<Map<String,Integer>> getCanRentCountAndCanReturnCount(HttpServletRequest request,Integer partnerId){
        return new JsonResult<>(powerService.getCanRentCountAndCanReturnCount(partnerId));
    }

    @PostMapping("/rentPower.do")
    public JsonResult rentPower(HttpServletRequest request,Integer partnerId){
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && partnerId != null && partnerId != 0){
            if (userService.findUserByUserId(user.getUserId()).getUserBalance()>0){
                return powerService.rentPower(user,partnerId);
            }else {
                new JsonResult(ResultEnum.NO_ENOUGH_BALANCE.getCode(),ResultEnum.NO_ENOUGH_BALANCE.getMsg());
            }
        }
        return new JsonResult(ResultEnum.NEED_LOGIN.getCode(),ResultEnum.NEED_LOGIN.getMsg());
    }

    @PostMapping("/returnPower.do")
    public JsonResult returnPower(HttpServletRequest request,Integer partnerId){
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && partnerId != null && partnerId != 0){
            return powerService.returnPower(user,partnerId);
        }
        return new JsonResult(ResultEnum.NEED_LOGIN.getCode(),ResultEnum.NEED_LOGIN.getMsg());
    }

    @GetMapping("/findPowers.do")
    public JsonResult<Power> findUsers(int pageSize, int pageNum, String sort, String order, String hubId, String powerId, String status){
        Map<String,Object> param = new HashMap<>();
        param.put("powerId",powerId);
        param.put("hubId",hubId);
        param.put("powerState",status);
        return new JsonResult<>(powerService.findPower(pageSize,pageNum,sort,order,param));
    }

    @DeleteMapping("/deleteOrders.do")
    public JsonResult deleteOrders(HttpServletRequest request,@RequestBody List<PowerDTO> orders){
        if (powerService.deletePowerByList(orders)==orders.size()){
            return new JsonResult<>("删除成功");
        }else{
            return new JsonResult<>("删除失败");
        }
    }
}
