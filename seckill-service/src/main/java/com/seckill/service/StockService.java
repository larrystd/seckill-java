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

    /**
     * 根据库存 ID 查询数据库库存信息（悲观锁）
     * @param id
     * @return
     */
    Stock getStockByIdForUpdate(int id);

    /**
     * 获取剩余库存：查数据库
     * @param id
     * @return
     */
    int getStockCountByDB(int id);

    /**
     * 获取剩余库存: 查缓存
     * @param id
     * @return
     */
    Integer getStockCountByCache(int id);
    // 插入缓存
    public void setStockCountToCache(int id, int count);
}
