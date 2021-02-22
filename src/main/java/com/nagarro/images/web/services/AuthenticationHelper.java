package com.nagarro.images.web.services;

import com.nagarro.images.web.dao.UsersDAO;
import com.nagarro.images.web.model.User;

public class AuthenticationHelper {

    public static boolean validate(String userName, String password) {
        UsersDAO dao = UsersDAO.getInstance();
        User user = dao.getUserByNameAndPassword(userName, password);
        return user != null;
    }

}
