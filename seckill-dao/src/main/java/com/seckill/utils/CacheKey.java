package com.seckill.utils;

public enum CacheKey {

    HASH_KEY("seckill_v1_user_hash"),
    LIMIT_KEY("seckill_v1_user_limit"),
    STOCK_COUNT("seckill_v1_stock_count"),
    USER_HAS_ORDER("seckill_v1_user_has_order");

    private String key;
    private CacheKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
}