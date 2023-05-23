package services;

import dataaccess.UserDB;
import models.User;

public class AccountService {
    
    public User login(String email, String password) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword()) && user.getActive() == true) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
}
