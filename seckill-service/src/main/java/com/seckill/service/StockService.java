package com.seckill.service;

import com.seckill.dao.Stock;;

public interface StockService {
    
    /**
     * 根据库存 ID 查询数据库库存信息
     * @param id
     * @return
     */
    Stock getStockById(int id);

    /**
     * 更新数据库库存信息
     * @param stock
     * return
     */
    int updateStockById(Stock stock);

    int updateStockByOptimistic(Stock stock);

}
