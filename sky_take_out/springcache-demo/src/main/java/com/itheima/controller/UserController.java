package com.itheima.controller;

import com.itheima.entity.User;
import com.itheima.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

//    @PostMapping
//    public User save(@RequestBody User user){
//        userMapper.insert(user);
//        return user;
//    }

    @DeleteMapping
    @CacheEvict(cacheNames = "userCache",key = "#id")//删除某个key对应的缓存数据
    public void deleteById(Long id){

        userMapper.deleteById(id);
    }

	@DeleteMapping("/delAll")
    @CacheEvict(cacheNames = "userCache",allEntries = true)//删除usercache下所有的缓存数据
    public void deleteAll(){
        userMapper.deleteAll();
    }

    @GetMapping
    public User getById(Long id){
        User user = userMapper.getById(id);
        return user;
    }

    /**
     * Cacheput: 将方法返回值放入缓存
     * value:缓存的名称，每个缓存名称下面可以有多个key
     * key:缓存的key
     *
     * @param user
     * @return
     */
    @PostMapping
    @CachePut(value = "userCache",key = "#user.id")
    public User save(@RequestBody User user){
        userMapper.insert(user);
        return user;
    }
}
