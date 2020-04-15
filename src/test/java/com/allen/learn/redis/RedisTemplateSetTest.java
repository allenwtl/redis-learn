package com.allen.learn.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RedisTemplateSetTest {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSETAdd(){
        String key = "allen.set.add";
        redisTemplate.opsForSet().add(key, "113", "111", "3454", 5);
    }

    @Test
    public void testSETRemove(){
        String key = "allen.set.add";
        Long result = redisTemplate.opsForSet().remove(key, "123");
        System.out.println(result);
    }

    @Test
    public void testSETPop(){
        String key = "allen.set.add";
        redisTemplate.opsForSet().pop(key, 1);
    }

    @Test
    public void testSETMove(){
        testSETAdd();
        String key = "allen.set.add";
        String destKey = "allen.set.add2";
        redisTemplate.opsForSet().move(key, 5, destKey);
    }

    @Test
    public void testSETSize(){
        String key = "allen.set.add";
        redisTemplate.opsForSet().size(key);
    }

    @Test
    public void testSETIsMember(){
        String key = "allen.set.add";
        Object o = "123";
        redisTemplate.opsForSet().isMember(key, o);
    }

    @Test
    public void testSETIntersect(){
        String key = "allen.set.add";
        String otherKey = "123";
        redisTemplate.opsForSet().intersect(key, otherKey);

        List<String> otherKeys = new ArrayList<>();
        redisTemplate.opsForSet().intersect(key, otherKeys);
    }

    @Test
    public void testSETUnion(){
        String key = "allen.set.add";
        String otherKey = "";
        redisTemplate.opsForSet().union(key, otherKey);
    }

    @Test
    public void testSETUnionAndStore(){
        String key = "allen.set.add";
        String otherKey = "";
        String destKey = "";
        redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }

    @Test
    public void testSETDifference(){
        String key = "allen.set.add";
        String otherKey = "";
        redisTemplate.opsForSet().difference(key, otherKey);
    }

    @Test
    public void testSETMembers(){
        String key = "allen.set.add";
        redisTemplate.opsForSet().members(key);
    }

    @Test
    public void testSETRandomMember(){
        String key = "allen.set.add";
        List values = redisTemplate.opsForSet().randomMembers(key, 4);
        System.out.println(values);
    }

}
