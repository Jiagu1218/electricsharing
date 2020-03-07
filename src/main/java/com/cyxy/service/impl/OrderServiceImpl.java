package com.cyxy.service.impl;

import com.cyxy.dao.OrderDao;
import com.cyxy.entity.Order;
import com.cyxy.entity.OrderDTO;
import com.cyxy.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public PageInfo<OrderDTO> findOrder(int pageSize, int pageNum, String sort, String order, Map map) {
        PageHelper.startPage(pageNum, pageSize, sort+"	"+order);
        return new PageInfo<OrderDTO>(orderDao.findOrder(map));
    }

    @Override
    public int deleteOrders(List<OrderDTO> list) {
        if (list == null || list.size() == 0){
            return 0;
        }
        List<Integer> orderIds = new ArrayList<>();
        for (OrderDTO orderDTO : list){
            orderIds.add(orderDTO.getOrderId());
        }
        return orderDao.deleteOrders(orderIds);
    }

    @Override
    public  PageInfo<Order> getPersonalOrders(int pageSize, int pageNum, String sort, String order, int userId) {
        PageHelper.startPage(pageNum, pageSize, sort+"	"+order);
        return new PageInfo<Order>(orderDao.getPersonalOrders(userId));
    }
}
