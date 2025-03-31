package com.seckill.service;

import com.seckill.dao.Stock;
import com.seckill.dao.StockOrder;
import com.seckill.dao.User;
import com.seckill.mapper.StockOrderMapper;
import com.seckill.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private StockService stockService;
    
    @Autowired
    private StockOrderMapper orderMapper;

    @Override
    public int createWrongOrder(int sid) {
        //校验库存
        Stock stock = checkStock(sid);
        //扣库存
        saleStock(stock);
        //创建订单
        int id = createOrder(stock);
        return id;
    }

    @Override
    public int createOptimisticOrder(int sid) {
        //校验库存
        Stock stock = checkStock(sid);
        //乐观锁更新库存
        saleStockOptimistic(stock);
        //创建订单
        int id = createOrder(stock);
        return stock.getCount() - (stock.getSale()+1);
    }

    /**
     * 检查库存
     * @param sid
     * @return
     */
    private Stock checkStock(int sid) {
        Stock stock = stockService.getStockById(sid);
        if (stock.getSale().equals(stock.getCount())) {
            throw new RuntimeException("库存不足");
        }
        return stock;
    }

    /**
     * 更新库存
     * @param stock
     */
    private void saleStock(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        stockService.updateStockById(stock);
    }

    private void saleStockOptimistic(Stock stock) {
        LOGGER.info("查询数据库，尝试更新库存");
        int count = stockService.updateStockByOptimistic(stock);
        if (count == 0){
            throw new RuntimeException("并发更新库存失败，version不匹配");
        }
    }
    /**
     * 创建订单
     * @param stock
     * @return
     */
    private int createOrder(Stock stock) {
        StockOrder order = new StockOrder();
        order.setSid(stock.getId());
        order.setName(stock.getName());
        return orderMapper.insertSelective(order);
    }
}
