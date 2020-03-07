package com.cyxy.controller;

import com.cyxy.entity.JsonResult;
import com.cyxy.entity.Order;
import com.cyxy.entity.OrderDTO;
import com.cyxy.entity.User;
import com.cyxy.enums.ResultEnum;
import com.cyxy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/findOrder.do")
    public JsonResult<OrderDTO> findOrder(int pageSize, int pageNum, String sort, String order, String orderId, String userName, String status){
        Map<String,Object> param = new HashMap<>();
        param.put("orderId",orderId);
        param.put("userName",userName);
        param.put("status",status);
        return new JsonResult<OrderDTO>(orderService.findOrder(pageSize,pageNum,sort,order,param));
    }

    @DeleteMapping("/deleteOrders.do")
    public JsonResult deleteOrders(HttpServletRequest request,@RequestBody List<OrderDTO> orders){
        if (orderService.deleteOrders(orders)==orders.size()){
            return new JsonResult<>("删除成功");
        }else{
            return new JsonResult<>("删除失败");
        }
    }

    @GetMapping("/getPersonalOrders.do")
    public JsonResult<Order> getPersonalOrders(HttpServletRequest request, int pageSize, int pageNum, String sort, String order){
        User user = (User) request.getSession().getAttribute("user");
        if (user != null){
            return new JsonResult(orderService.getPersonalOrders(pageSize, pageNum, sort, order,user.getUserId()));
        }
        return new JsonResult(ResultEnum.NEED_LOGIN.getCode(),ResultEnum.NEED_LOGIN.getMsg());
    }
}
