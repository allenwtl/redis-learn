package com.allen.learn.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testStrSet(){
        String key = "allen.test-string-value";
        String value = "Hello Redis";
//        stringRedisTemplate.opsForValue().set("allen.test-string-value", "Hello Redis");
//        stringRedisTemplate.opsForValue().set("allen.test-string-value", "Hello Redis", 10, TimeUnit.SECONDS);
        Duration duration = Duration.of(100, ChronoUnit.SECONDS);
//        boolean success = stringRedisTemplate.opsForValue().setIfAbsent("allen.test-string-value-2", "Hello Redis", duration);
        boolean success = stringRedisTemplate.opsForValue().setIfPresent(key, value, duration);
        Map<String, String> map = new HashMap<>();
        map.put(key, value);
        map.put("allen.a", value);
        map.put("allen.b", value);
        map.put("allen.c", value);
//        stringRedisTemplate.opsForValue().multiSet(map);
//        System.out.println(success);
        stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    @Test
    public void testStrGet(){
        String value = stringRedisTemplate.opsForValue().get("allen.test-string-value");
        System.out.println(value);
        List<String> keys = new ArrayList<>();
        keys.add("allen.a");
        keys.add("allen.b");
        List<String> values = stringRedisTemplate.opsForValue().multiGet(keys);
        System.out.println(values);
    }


    @Test
    public void testListLeftPush(){
        String key = "TestList";
        //redisTemplate.opsForList().leftPush(key, "TestLeftPush");
        redisTemplate.opsForList().leftPushAll(key, "TestLeftPush4", "TestLeftPush5", "TestLeftPush6");
    }

    @Test
    public void testListRightPush(){
        String key = "TestList";
        //redisTemplate.opsForList().leftPush(key, "TestLeftPush");
        redisTemplate.opsForList().rightPushAll(key, "TestRightPush4", "TestRightPush5", "TestRightPush6");
    }

    @Test
    public void testListRange(){
        String key = "TestList";
        List<String> strings = redisTemplate.opsForList().range(key, 3,4);
        System.out.println(strings);
    }

    @Test
    public void testTrim(){
        String key = "TestList";
        redisTemplate.opsForList().trim(key, 0, 2);
    }

    @Test
    public void testSize(){
        String key = "TestList";
        Long size = redisTemplate.opsForList().size(key);
        System.out.println(size);
    }

    @Test
    public void testListSet(){
        String key = "TestList";
        redisTemplate.opsForList().set(key, 1, "TestSetList1");
    }

    @Test
    public void testListRemove(){
        String key = "TestList";
        Long result = redisTemplate.opsForList().remove(key, 1, "TestSetList1");
        System.out.println(result);
    }

    @Test
    public void testListIndex(){
        String key = "TestList";
        Object result = redisTemplate.opsForList().index(key, 1);
        System.out.println(result);
    }

    @Test
    public void testListLeftPop(){
        String key = "TestList";
        Object result = redisTemplate.opsForList().leftPop(key, 100, TimeUnit.SECONDS);
        System.out.println(result);
        testListIndex();
    }
}
