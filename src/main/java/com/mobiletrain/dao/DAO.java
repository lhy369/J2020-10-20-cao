package com.mobiletrain.dao;

import com.mobiletrain.domain.User;

import java.util.List;

public interface DAO {
    User queryById(int id);

    List<User> queryAll();
}
