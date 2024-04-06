package com.example.redisdemo;

import com.example.redisdemo.poko.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.dynamic.loading.ClassInjector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.security.PrivilegedAction;
import java.util.Map;

@SpringBootTest
class RedisDemoApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name","huge");
        //获取string数据2
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    void contextLoads2() {
        redisTemplate.opsForValue().set("user:100",new User("huge",21));
        //获取string数据2
        Object name = redisTemplate.opsForValue().get("user:100");
        System.out.println(name);
    }

    /**
     * 不使用JSON序列化器来处理value，
     * 而是统一使用String序列化器，要求只能存储String类型的key和value。
     * 当需要存储Java对象时，手动完成对象的序列化和反序列化。
     */

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 手动序列化 Springmvc默认处理json的工具
    private static final ObjectMapper mapper = new ObjectMapper();
    @Test
    void contextLoads3() throws JsonProcessingException {
        // 创建对象
        User user = new User("虎哥", 21);
        // 手动序列化
        String json = mapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user:200",json);
        //获取string数据2
        String jsonUser = stringRedisTemplate.opsForValue().get("user:200");
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println(user1);
    }


    @Test
    void contextLoads4() throws JsonProcessingException {
        stringRedisTemplate.opsForHash().put("user:400","name","aaa");
        stringRedisTemplate.opsForHash().put("user:400","age","12");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println(entries);
    }


}
