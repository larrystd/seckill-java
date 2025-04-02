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

    /**
     * 创建正确订单：下单悲观锁 for update
     * @param sid
     * @return
     * @throws Exception
     */
    public int createPessimisticOrder(int sid);
}
