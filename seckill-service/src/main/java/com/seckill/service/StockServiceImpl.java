package com.seckill.service;

import com.seckill.dao.Stock;
import com.seckill.dao.StockOrder;
import com.seckill.mapper.StockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class StockServiceImpl implements StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockMapper stockMapper;

    @Override
    public Stock getStockById(int id) {
        return stockMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStockById(Stock stock) {
        return stockMapper.updateByPrimaryKeySelective(stock);
    }

    @Override
    public int updateStockByOptimistic(Stock stock) {
        return stockMapper.updateByOptimistic(stock);
    }

    @Override
    public Stock getStockByIdForUpdate(int id) {
        return stockMapper.selectByPrimaryKeyForUpdate(id);
    }
}
