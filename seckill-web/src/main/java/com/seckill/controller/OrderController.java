package com.seckill.controller;

import com.seckill.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 下单接口：导致超卖的错误示范, 并发下单，导致超卖
     * @param sid
     * @return
     */
    @RequestMapping("/createWrongOrder/{sid}")
    @ResponseBody
    public String createWrongOrder(@PathVariable int sid) {
        int id = 0;
        try {
            id = orderService.createWrongOrder(sid);
            LOGGER.info("创建订单id: [{}]", id);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
        }
        return String.valueOf(id);
    }


    /**
     * 乐观锁更新库存, 并发安全
     * @param sid
     * @return
     */
    @RequestMapping("/createOptimisticOrder/{sid}")
    @ResponseBody
    public String createOptimisticOrder(@PathVariable int sid) {
        int id;
        try {
            id = orderService.createOptimisticOrder(sid);
            LOGGER.info("购买成功，剩余库存为: [{}]", id);
        } catch (Exception e) {
            LOGGER.error("购买失败：[{}]", e.getMessage());
            return "购买失败，库存不足";
        }
        return String.format("购买成功，剩余库存为：%d", id);
    }
}
