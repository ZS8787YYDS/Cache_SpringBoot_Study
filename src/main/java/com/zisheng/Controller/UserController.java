package com.zisheng.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zisheng.Entity.User;
import com.zisheng.ResponseResult.Result;
import com.zisheng.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    /**
     * 插入功能
     * @param user
     * @return
     */
    @CachePut(value = "user_",key = "#user.id")
    @PostMapping
    public Result insertUser(@RequestBody User user)
    {
        log.info("插入方法执行了！！！");
        userService.insertUser(user);
        return Result.success();
    }

    /**
     * 删除功能
     * @param user
     * @return
     */
    @CacheEvict(value = "user_",key = "#user.id")
    @DeleteMapping
    public Result deleteUser(User user)
    {
        log.info("要删除的用户id为：{}",user.getId());
        userService.removeById(user);
        return Result.success();
    }
    @CacheEvict(value = "user_",key = "#user.id")
    /**
     * 更新功能
     */
    @PutMapping
    public Result updateUser(User user)
    {
        log.info("要更新的用户信息为：{}",user);
        userService.updateUser(user);
        return Result.success();
    }
    /**
     * 查询功能
     * @param id
     * @return
     */
    @Cacheable(value = "users_",key = "#id",unless = "#result.data == null")
    @GetMapping("/{id}")
    public Result findUser(@PathVariable Long id)
    {
        log.info("查询的用户id为：{}",id);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(id != null,User::getId,id);
        List<User> users = userService.list(lambdaQueryWrapper);
        return Result.success(users);
    }

}
