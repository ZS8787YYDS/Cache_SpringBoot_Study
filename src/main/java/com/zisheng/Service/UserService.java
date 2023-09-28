package com.zisheng.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zisheng.Entity.User;

public interface UserService extends IService<User> {
    /**
     * 插入功能
     * @param user
     */
    void insertUser(User user);

    /**
     * 修改功能
     * @param user
     */

    void updateUser(User user);
}
