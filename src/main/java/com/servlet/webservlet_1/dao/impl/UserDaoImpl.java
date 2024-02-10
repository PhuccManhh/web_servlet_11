package com.servlet.webservlet_1.dao.impl;

import com.servlet.webservlet_1.dao.IUserDao;
import com.servlet.webservlet_1.model.UserModel;
import com.servlet.webservlet_1.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public List<UserModel> getAllUser() {
        String sql ="select * from containslist";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<UserModel> modelList = new ArrayList<>();
        try {
            connection = ConnectionUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            UserModel userModel = null;
            while (resultSet.next()){
                userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setFullName(resultSet.getString("full_name"));
                userModel.setUsername(resultSet.getString("username"));
                userModel.setAddress(resultSet.getString("address"));
                userModel.setRoleId(resultSet.getInt("roleId"));
                userModel.setAge(resultSet.getInt("age"));
                userModel.setSex(resultSet.getInt("sex"));
                modelList.add(userModel);
            }
        }catch (SQLException e){
            if (connection != null){
                try {
                    connection.rollback();
                }catch (SQLException ep){
                    throw new RuntimeException(ep);
                }
            }
            colseConneciton(connection,preparedStatement, resultSet);
            e.printStackTrace();
        }finally{
            colseConneciton(connection,preparedStatement, resultSet);
        }
        return modelList;
    }

    @Override
    public void insertUser(UserModel userModel) {
       String sql = "INSERT INTO userlist_v1.containslist (full_name, username, password, address, age, sex)\n" +
               "VALUES (?, ?, ?, ?, ?, ?)";
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       try {
           connection = ConnectionUtils.getConnection();
           connection.setAutoCommit(false);
           preparedStatement=connection.prepareStatement(sql);
           preparedStatement.setString(1, userModel.getFullName());
           preparedStatement.setString(2, userModel.getUsername());
           preparedStatement.setString(3, userModel.getPassword());
           preparedStatement.setString(4, userModel.getAddress());
           preparedStatement.setInt(5, userModel.getAge());
           preparedStatement.setInt(6, userModel.getSex());
           int p = preparedStatement.executeUpdate();
           System.out.println("data" + p);
           connection.commit();

       }catch (SQLException e){
           if (connection != null){
               try {
                   connection.rollback();
               }catch (SQLException et){
                   throw new RuntimeException(et);
               }
           }
           colseConneciton(connection,preparedStatement, null);
           e.printStackTrace();
       }finally{
           colseConneciton(connection,preparedStatement, null);
       }
    }

    @Override
    public UserModel findUserById(Integer userId) {
        String sql = "select * from containslist where id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserModel updateUser = new UserModel();
        try {
            connection = ConnectionUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                updateUser.setId(resultSet.getInt("id"));
                updateUser.setFullName(resultSet.getString("full_name"));
                updateUser.setUsername(resultSet.getString("username"));
                updateUser.setPassword(resultSet.getString("password"));
                updateUser.setAddress(resultSet.getString("address"));
                updateUser.setAge(resultSet.getInt("age"));
                updateUser.setSex(resultSet.getInt("sex"));
            }

        }catch (Exception e){
            e.printStackTrace();
            colseConneciton(connection, preparedStatement,resultSet);
        }finally {
            colseConneciton(connection, preparedStatement,resultSet);

        }
        return updateUser;

    }

    @Override
    public void updateUser(UserModel userModel) {
       String sql = "UPDATE userlist_v1.containslist \n" +
               "SET full_name = ?, \n" +
               "    username = ?, \n" +
               "    password = ?, \n" +
               "    address = ?, \n" +
               "    age = ?, \n" +
               "    sex = ? WHERE id = ?";
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       try {
           connection = ConnectionUtils.getConnection();
           connection.setAutoCommit(false);
           preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1,userModel.getFullName());
           preparedStatement.setString(2,userModel.getUsername());
           preparedStatement.setString(3,userModel.getPassword());
           preparedStatement.setString(4,userModel.getAddress());
           preparedStatement.setInt(5,userModel.getAge());
           preparedStatement.setInt(6,userModel.getSex());
           preparedStatement.setInt(7,userModel.getId());
           int seach = preparedStatement.executeUpdate();
           System.out.println("update" + seach + "reacord");
           connection.commit();
       }catch (Exception e){
           e.printStackTrace();
           if (connection != null){
               try {
                   connection.rollback();
               } catch (SQLException ex) {
                   throw new RuntimeException(ex);
               }
           }
           colseConneciton(connection, preparedStatement,null);
       }finally {
           colseConneciton(connection, preparedStatement,null);

       }
    }


    public void colseConneciton (Connection connection, PreparedStatement pre, ResultSet rs){
        if (connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }if (pre != null){
            try {
                pre.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }if (rs != null){
            try {
                rs.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }
}
