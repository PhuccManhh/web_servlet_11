package com.servlet.webservlet_1.service.impl;

import com.servlet.webservlet_1.dao.IUserDao;
import com.servlet.webservlet_1.dao.impl.UserDaoImpl;
import com.servlet.webservlet_1.model.UserModel;
import com.servlet.webservlet_1.service.IUserService;
import com.servlet.webservlet_1.utils.Connstans;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserServiceImpl implements IUserService {
    @Override
    public List<UserModel> getAllUser() {
        IUserDao iUserDao = new UserDaoImpl();
        List<UserModel> userModels = iUserDao.getAllUser();
         for (int p = 0; p < userModels.size(); p++){
             UserModel userModel = userModels.get(p);
           if (Connstans.MALE_CODE == userModel.getSex()){
               userModel.setSexName(Connstans.MALE_NAME);
           }else {
               userModel.setSexName(Connstans.FEMAE_NAME);
           }
         }
         return userModels;
    }

    @Override
    public void addUser(HttpServletRequest request) {
        UserModel userModel = getUserFromRequest(request);
        IUserDao iUserDao = new UserDaoImpl();
        iUserDao.insertUser(userModel);
    }

    private UserModel getUserFromRequest(HttpServletRequest request) {
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        UserModel userModel = new UserModel();
        userModel.setFullName(fullName);
        userModel.setUsername(username);
        userModel.setAddress(address);
        userModel.setPassword(password);
        try {
            userModel.setAge(Integer.parseInt(age));
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            userModel.setSex(Integer.parseInt(sex));
        }catch (Exception e){
            e.printStackTrace();
        }
        IUserDao iUserDao = new UserDaoImpl();
        iUserDao.insertUser(userModel);
        return userModel;
    }

    @Override
    public UserModel finUserId(String userId) {
        try{
            Integer id = Integer.parseInt(userId);
            IUserDao iUserDao = new UserDaoImpl();
  // chú ý dòng 62 servlet không cơ chế quản lý object // spring sẽ hỗ trợ
            UserModel userModel = iUserDao.findUserById(id);
            return userModel;
        }catch (Exception e){
          e.printStackTrace();
            System.out.println("user not valid" + e.getMessage());
        }
        return new UserModel();
    }

    @Override
    public void updateUser(HttpServletRequest request, String userId) {
        UserModel userModel = getUserFromRequest(request);
        userModel.setId(Integer.parseInt(userId));
        IUserDao iUserDao = new UserDaoImpl();
        iUserDao.updateUser(userModel);
    }


}
