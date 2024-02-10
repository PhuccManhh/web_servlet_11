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

@WebServlet(name = "AddUser", urlPatterns = "/Add_User")
public class UserAddServlet extends HttpServlet {
    // hàm xử lý việc hiển thị giao diện người dùng
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userId = req.getParameter("userId1");
        if (userId != null && !userId.isEmpty()){
            // request update thông tin
            IUserService iUserService = new UserServiceImpl() ;
            // lấy ra thông tin user trong db
            UserModel userUpdate = iUserService.finUserId(userId);
            //gắn user vừa lâ được vào view
            req.setAttribute("updateId",userUpdate);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/admin/User_Add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        IUserService iUserService = new UserServiceImpl();
        String userId = req.getParameter("userId");
        if (userId != null && userId.isEmpty()){
            iUserService.addUser(req);
        }else {
            iUserService.updateUser(req,userId);
        }
        // redirect sang url list user
        resp.sendRedirect(req.getContextPath() + "/User_List");
    }
}
