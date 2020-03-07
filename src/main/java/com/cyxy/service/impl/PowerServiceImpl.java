package com.cyxy.service.impl;

import com.cyxy.dao.HubDao;
import com.cyxy.dao.OrderDao;
import com.cyxy.dao.PowerDao;
import com.cyxy.dao.UserDao;
import com.cyxy.entity.*;
import com.cyxy.enums.ResultEnum;
import com.cyxy.service.PowerService;
import com.cyxy.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerDao powerDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private HubDao hubDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Power> findPowerByHubId(int hubId) {
        return powerDao.findPowerByHubId(hubId);
    }

    @Override
    public Map<String, Integer> getCanRentCountAndCanReturnCount(int partnerId) {
        Map<String,Integer> map = new HashMap<>();
        map.put("canRentCount",powerDao.canRentPowerCount(partnerId));
        map.put("canReturnCount", powerDao.canReturnPowerCount(partnerId));
        return map;
    }

    @Override
    public JsonResult rentPower(User user, int partnerId) {
        List<Power> powers = powerDao.cnaRentPowerList(partnerId);
        if (powers !=null && powers.size() !=0){
            if (needCompleteOrder(user).size()==0){
                Power power=powers.get(0);
                //修改状态
                power.setPowerState("外借");
                power.setHubId(null);
                powerDao.updatePowerState(power);
                //创建订单
                Order order = new Order();
                order.setUserId(user.getUserId());
                order.setPowerId(power.getPowerId());
                order.setOrderRentalTime(new Date());
                orderDao.insertOrder(order);
                return new JsonResult(ResultEnum.RENT_SUCCESS.getCode(),ResultEnum.RENT_SUCCESS.getMsg());
            }
            return new JsonResult(ResultEnum.HAVE_NEED_COMPLETE_ORDER.getCode(),ResultEnum.HAVE_NEED_COMPLETE_ORDER.getMsg());
        }
        return new JsonResult(ResultEnum.NO_POWER_CAN_RENT.getCode(),ResultEnum.HAVE_NEED_COMPLETE_ORDER.getMsg());
    }

    @Override
    public JsonResult returnPower(User user, int partnerId) {
        List<Order> needCompleteOrders = needCompleteOrder(user);
        if (needCompleteOrders.size()>0){
            //查出可用于归还的hub
            List<Hub> hubs = hubDao.findCanUserReturnHub(partnerId);
            if (hubs != null && hubs.size()>0){
                Date returnTime = new Date();
                //查出需要归还的订单
                Order order = needCompleteOrders.get(0);
                //更新power状态
                Power power = new Power();
                power.setPowerId(order.getPowerId());
                power.setPowerState("正常");
                power.setPowerFull(false);
                power.setOrderReturnTime(returnTime);
                power.setHubId(hubs.get(0).getHubId());
                powerDao.updatePowerState(power);
                //计算扣除费用
                double price = calculatePrice(order.getOrderRentalTime(),returnTime);
                userDao.updateUserBalance(user.getUserId(),price);
                //更新订单
                order.setOrderReturnTime(returnTime);
                order.setOrderSum(price);
                orderDao.updateOrderAfterReturn(order);
                return new JsonResult(ResultEnum.RETURN_POWER_SUCCESS.getCode(),ResultEnum.RETURN_POWER_SUCCESS.getMsg());
            }
            return new JsonResult(ResultEnum.NO_FREE_HUB_PORT.getCode(),ResultEnum.NO_FREE_HUB_PORT.getMsg());
        }
        return new JsonResult(ResultEnum.NO_NEED_COMPLETE_ORDER.getCode(),ResultEnum.NO_NEED_COMPLETE_ORDER.getMsg());
    }

    @Override
    public PageInfo<Power> findPower(int pageSize, int pageNum, String sort, String order, Map map) {
        PageHelper.startPage(pageNum, pageSize, sort+"	"+order);
        List<Power> powers = powerDao.findPower(map);
        return new PageInfo<>(powers);
    }

    @Override
    public int deletePowerByList(List<PowerDTO> list) {
        if (list == null || list.size() == 0){
            return 0;
        }
        List<Integer> orderIds = new ArrayList<>();
        for (PowerDTO powerDTO : list){
            orderIds.add(powerDTO.getPowerId());
        }
        return powerDao.deletePowers(orderIds);
    }

    //TODO　优化
    /**计算金额
     * @param fromDate
     * @param toDate
     * @return
     */
    private double calculatePrice(Date fromDate,Date toDate){
        int price;
        long hour = DateUtils.getHourByMinusDate(fromDate,toDate)+1L;
        if (hour<24L){ //小于一天
            price = new Long(hour).intValue() * 2;
            if (price>10){
                return 10;
            }else{
                return price;
            }
        }else{
            int remainder = new Long(hour%24).intValue();
            int remainderPrice = remainder * 2;
            remainderPrice = remainderPrice>10?10:remainderPrice;
            int dayPrice = new Long(hour/24).intValue() * 10;
            return dayPrice+remainderPrice;
        }
    }

    private List<Order> needCompleteOrder(User user){
        return orderDao.needCompleteOrder(user.getUserId());
    }
}
