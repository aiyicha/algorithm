//package com.aiyicha.algorithm.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.*;
//
//
///**
// * Created by fangquanwei on 2017/9/28.
// */
//@RestController
//public class CommunityController{
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @RequestMapping("/hello")
//    public String hello() {
//        String ss = "hello world";
//        stringRedisTemplate.opsForValue().set("hello", ss);
//        return ss;
//    }
//
//}
