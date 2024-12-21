package com.zy.enginee.distribute.lock.redis.controller;

import com.zy.enginee.distribute.lock.redis.service.impl.SetNxLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author zy
 * @Date 2024/12/21 23:04
 **/
@RestController
@RequestMapping("engine/lock")
public class AcquireLockController {

    @Autowired
    private SetNxLock setNxLock;

    @GetMapping("/test")
    public String test(){
        return "hello world";
    }

    @GetMapping("/getSetNxLock")
    public String getSetNxLock(@RequestParam("key")String key){
       return setNxLock.getLock(key) ? "success" : "fail";
    }

}
