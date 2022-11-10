package com.example.login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.dbUtil.dbConnection;

public class LoginModel {
    
    Connection conn = null;

    public LoginModel(){
        this.conn = dbConnection.getConnection();

        if(this.conn == null){
            System.exit(1);
        }

    }

    public boolean isDatabaseConnected(){
        return this.conn != null;
    }

    public boolean isLogin(String username, String password){
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM login_tbl WHERE username = ? AND password = ?";

        try {
            statement = this.conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            
            resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addUser(String username, String password) {
        String query = "INSERT INTO login_tbl (username, password) VALUES (?,?)";
        PreparedStatement statement = null;
    
        try {
          statement = conn.prepareStatement(query);
          
          statement.setString(1, username);
          statement.setString(2, password);
    
          statement.executeUpdate();
          
        } catch (SQLException e) {
          e.printStackTrace();
        } finally {
          try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
      }

      public boolean isExist(String username) {
        String query = "SELECT * FROM login_tbl WHERE username = ?";
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conn.prepareStatement(query);

            statement.setString(1, username);

            result = statement.executeQuery();

            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
      }
    
}