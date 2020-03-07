package com.cyxy.service;

import com.cyxy.entity.Order;
import com.cyxy.entity.OrderDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface OrderService {
    PageInfo<OrderDTO> findOrder(int pageSize, int pageNum, String sort, String order, Map map);
    int deleteOrders(List<OrderDTO> list);
    PageInfo<Order> getPersonalOrders(int pageSize, int pageNum, String sort, String order, int userId);
}
