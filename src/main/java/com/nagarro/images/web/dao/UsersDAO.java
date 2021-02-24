package com.nagarro.images.web.dao;

import com.nagarro.images.web.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersDAO {

    public static UsersDAO getInstance() {

        return new UsersDAO();
    }

    public User getUserByNameAndPassword(String userName, String password) {
        User user = null;

        try {
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE user_name =  ? AND password = ? ");

            stmt.setString(1, userName);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                user = new User();

                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
            }

            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return user;
    }

}
