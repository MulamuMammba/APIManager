package com.mammba.APIManager.Controller.Auth;

import com.mammba.APIManager.Model.Users;
import com.mammba.APIManager.Repository.UsersTable;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public class Authentication {

    public boolean register( Users user){
        UsersTable.createUser(user);

        return true;
    }

    public String login(String email, String password, HttpSession session, Model model){
        Users user = UsersTable.getUserByEmail(password);

        if (user.getEmail() == null) {
            model.addAttribute("response", "Account not found! or incorrect credentials, go back and try again");

            return "login";

        } else if (user.getEmail().equals(email) && user.getPassword().equals(password)){
            session.setAttribute("currentUser",user);
            model.addAttribute("user",user);
            return "dashboard";
        }else {
            model.addAttribute("response", "Account not found! or incorrect credentials, go back and try again");

            return "login";
        }



    }
}