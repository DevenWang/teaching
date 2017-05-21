package com.whut.teaching.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
    private static JedisPool pool = null;

    static {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(100);
            config.setMaxIdle(20);
            config.setMaxWaitMillis(10000);
            pool = new JedisPool(config, "localhost", 6379, 2000);
        }

    }

    public static String get(String key) {
        Jedis jedis = pool.getResource();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    public static void set(String key, String value) {
        Jedis jedis = pool.getResource();
        jedis.setex(key, 3600 * 24 * 30, value);
        jedis.close();
    }

    public static void set(String key, String value, int exp) {
        Jedis jedis = pool.getResource();
        jedis.setex(key, exp, value);
        jedis.close();
    }

    public static void delete(String key) {
        Jedis jedis = pool.getResource();
        jedis.del(key);
        jedis.close();
    }
}
