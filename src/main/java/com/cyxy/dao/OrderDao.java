package com.cyxy.dao;

import com.cyxy.annotation.MyBatisDAO;
import com.cyxy.entity.Order;
import com.cyxy.entity.OrderDTO;

import java.util.List;
import java.util.Map;
@MyBatisDAO
public interface OrderDao {
    List<OrderDTO> findOrder(Map map);
    int deleteOrders(List<Integer> list);
    int insertOrder(Order order);
    List<Order> needCompleteOrder(int userId);
    int updateOrderAfterReturn(Order order);
    List<Order> getPersonalOrders(int userId);
}
