package com.allen.learn.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class RedisTemplateSetTest {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSETAdd(){
        String key = "allen.set.add3";
        redisTemplate.opsForSet().add(key, "11", "111", "344");
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
        List values = redisTemplate.opsForSet().pop(key, 1);
        System.out.println(values);
    }

    @Test
    public void testSETMove(){
//        testSETAdd();
        String key = "allen.set.add";
        String destKey = "allen.set.add3";
        Boolean moved = redisTemplate.opsForSet().move(key, 5, destKey);
        System.out.println(moved);
    }

    @Test
    public void testSETSize(){
        String key = "allen.set.add";
        Long size = redisTemplate.opsForSet().size(key);
        System.out.println(size);
    }

    @Test
    public void testSETIsMember(){
        String key = "allen.set.add";
        Object o = "111";
        Boolean isMember = redisTemplate.opsForSet().isMember(key, o);
        System.out.println(isMember);
    }

    @Test
    public void testSETIntersect(){
        String key = "allen.set.add";
        String otherKey = "allen.set.add3";
        Set values = redisTemplate.opsForSet().intersect(key, otherKey);
        System.out.println(values);
//        List<String> otherKeys = new ArrayList<>();
//        redisTemplate.opsForSet().intersect(key, otherKeys);
    }

    @Test
    public void testSETUnion(){
        String key = "allen.set.add";
        String otherKey = "allen.set.add3";
        Set values = redisTemplate.opsForSet().union(key, otherKey);
        System.out.println(values);
    }

    @Test
    public void testSETUnionAndStore(){
        String key = "allen.set.add";
        String otherKey = "allen.set.add3";
        String destKey = "allen.set.add.union.store";
        Long result = redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
        System.out.println(result);
    }

    @Test
    public void testSETDifference(){
        String key = "allen.set.add";
        String otherKey = "allen.set.add3";;
        Set values = redisTemplate.opsForSet().difference(key, otherKey);
        System.out.println(values);
    }

    @Test
    public void testSETMembers(){
        String key = "allen.set.add";
        Set values = redisTemplate.opsForSet().members(key);
        System.out.println(key+"="+values);

        key = "allen.set.add3";
        values = redisTemplate.opsForSet().members(key);
        System.out.println(key+"="+values);
    }

    @Test
    public void testSETRandomMember(){
        String key = "allen.set.add";
        List values = redisTemplate.opsForSet().randomMembers(key, 4);
        System.out.println(values);
    }

    @Test
    public void testSETDistinctRandomMembers(){
        String key = "allen.set.add";
        Set values = redisTemplate.opsForSet().distinctRandomMembers(key, 4);
        System.out.println(values);
    }
}
