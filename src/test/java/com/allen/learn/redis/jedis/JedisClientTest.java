package com.allen.learn.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClientTest {

    public static void main(String[] args) {
        //testJedisPool();
        testJedis();
    }

    private static void testJedisPool() {
        JedisPool jedisPool = generateJedisPool();
        try (Jedis jedis = jedisPool.getResource()) {
            String jedisKey = "Test.JedisPool.foo";
            jedis.set(jedisKey, "bar");
            String foobar = jedis.get(jedisKey);
            System.out.println(foobar);
        }

        System.out.println(jedisPool.getNumIdle());
    }

    private static JedisPool generateJedisPool() {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
        return pool;
    }

    private static void testJedis() {
        String jedisKey = "Test.Jedis.foo";
        Jedis jedis = generateJedis();
        jedis.set(jedisKey, "jedis.value");
        String value = jedis.get(jedisKey);
        System.out.println(value);
    }

    private static Jedis generateJedis() {
        Jedis jedis = new Jedis("localhost");
        return jedis;
    }


}
