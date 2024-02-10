package com.servlet.webservlet_1.service;

import com.servlet.webservlet_1.model.UserModel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {
    public List<UserModel> getAllUser();

    public void addUser(HttpServletRequest request);

    UserModel finUserId(String userId);

    void updateUser(HttpServletRequest request,String userId);
}
