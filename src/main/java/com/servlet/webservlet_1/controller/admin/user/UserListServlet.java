package com.servlet.webservlet_1.controller.admin.user;


import com.servlet.webservlet_1.model.UserModel;
import com.servlet.webservlet_1.service.IUserService;
import com.servlet.webservlet_1.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userlist", urlPatterns = "/User_List")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserService iUserService = new UserServiceImpl();
        List<UserModel> userModels = iUserService.getAllUser();
        req.setAttribute("userList", userModels);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/admin/User_List.jsp");
        requestDispatcher.forward(req, resp);
    }
}
