package com.mmall.demo2.server;

import com.mmall.demo2.model.User;

public interface UserService {
    User findByUsername(String username);
}
