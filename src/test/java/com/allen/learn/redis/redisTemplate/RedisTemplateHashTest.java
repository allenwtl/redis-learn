package com.allen.learn.redis.redisTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.*;

@SpringBootTest
public class RedisTemplateHashTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testHashPut(){
        String key = "TestHashKey3";
        String hashKey = "HashKeyTest3";
        String hashValue = "HashValueTest3";
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    @Test
    public void testHashPutAll(){
        String key = "TestHashKey3";
        Map<String, String> map = new HashMap<>();
        map.put("HashKeyTest4", "HashValueTest4");
        map.put("HashKeyTest5", "HashValueTest5");
        map.put("HashKeyTest6", "HashValueTest6");
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Test
    public void testHashPutIfAbsent(){
        String key = "TestHashKey3";
        String hashKey = "HashKeyTest6";
        String hashValue = "HashValueTest4";
        Boolean success = redisTemplate.opsForHash().putIfAbsent(key, hashKey, hashValue);
        System.out.println(success);
    }

    @Test
    public void testHashGet(){
        String key = "TestHashKey";
        String hashKey = "HashKeyTest";
        Object hashValue = redisTemplate.opsForHash().get(key, hashKey);
        System.out.println(hashValue);
    }


    @Test
    public void testHashKeys(){
        String key = "TestHashKey";
        Set hashKeys = redisTemplate.opsForHash().keys(key);
        System.out.println(hashKeys);
    }

    @Test
    public void testHashValue(){
        String key = "TestHashKey3";
        List values = redisTemplate.opsForHash().values(key);
        System.out.println(values);
    }

    @Test
    public void testHashEntries(){
        String key = "TestHashKey3";
        Map map = redisTemplate.opsForHash().entries(key);
        System.out.println(map);
    }

    @Test
    public void testHashMultiGet(){
        String key = "TestHashKey3";
        List<String> hashKeys = new ArrayList<>();
        hashKeys.add("HashKeyTest6");
        hashKeys.add("bbb");
        List<String> valueList = redisTemplate.opsForHash().multiGet(key, hashKeys);
        System.out.println(valueList);
    }

    @Test
    public void testHashIncrement(){
        String key = "TestHashKey3";
        String hashKey = "HashKeyTest9";
        Long result = redisTemplate.opsForHash().increment(key, hashKey, 2);
        System.out.println(result);
    }


    /**
     * todo 这个不明白
     */
    @Test
    public void testHashScan(){
        String key = "TestHashKey3";
        ScanOptions options = ScanOptions.scanOptions().count(2).build();
        Cursor<Map.Entry> entryCursor = redisTemplate.opsForHash().scan(key, options);
        System.out.println(entryCursor);
    }



}
