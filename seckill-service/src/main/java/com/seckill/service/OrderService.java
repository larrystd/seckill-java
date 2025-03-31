package com.seckill.service;

public interface OrderService {

    /**
     * 创建错误订单
     * @param sid
     *  库存ID
     * @return
     *  订单ID
     */
    public int createWrongOrder(int sid);

    public int createOptimisticOrder(int sid);

}
