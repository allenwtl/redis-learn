package com.allen.learn.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

@SpringBootTest
public class RedisTemplateZSetTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testAdd(){
        String key = "Test.ZSet.add";
        getZSetOperations().add(key, "111", 2);
        getZSetOperations().add(key, "112", 1);
        getZSetOperations().add(key, "113", 3);
    }

    @Test
    public void testRemove(){
        String key = "Test.ZSet.add";
        Long result = getZSetOperations().remove(key, "114");
        System.out.println(result);
    }


    private ZSetOperations getZSetOperations(){
        return redisTemplate.opsForZSet();
    }

}
