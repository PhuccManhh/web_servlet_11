package com.servlet.webservlet_1.dao;

import com.servlet.webservlet_1.model.UserModel;

import java.util.List;

public interface IUserDao {
    List<UserModel> getAllUser();
    void insertUser(UserModel userModel);
    UserModel findUserById(Integer userId);
    void updateUser(UserModel userModel);
}
