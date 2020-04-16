package com.allen.learn.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testLock() {
        String lockKey = "Test.lock.key.string";
        String lockValue = "1";
        Duration timeout = Duration.ofSeconds(50);
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, timeout);
        System.out.println(success);
    }

    @Test
    public void testLock2() {
        String lockKey = "Test.lock.key.string";
        String lockValue = "1";
        long timeout = 50;
        Boolean lockStat = (Boolean) redisTemplate.execute((RedisCallback) connection ->
                connection.set(lockKey.getBytes(Charset.forName("UTF-8")),
                        lockValue.getBytes(Charset.forName("UTF-8")),
                        Expiration.from(timeout, TimeUnit.SECONDS),
                        RedisStringCommands.SetOption.SET_IF_ABSENT));
        System.out.println(lockStat);

    }


    @Test
    public void testReleaseLock(){
        String lockKey = "Test.lock.key.string";
        String lockValue = "1";
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        boolean unLockStat = (boolean) redisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.eval(script.getBytes(), ReturnType.BOOLEAN, 1,
                        lockKey.getBytes(Charset.forName("UTF-8")), lockValue.getBytes(Charset.forName("UTF-8"))));
        System.out.println(unLockStat);
    }

    @Test
    public void testLockAndRelease(){
        this.testLock2();
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.testReleaseLock();
    }

}
