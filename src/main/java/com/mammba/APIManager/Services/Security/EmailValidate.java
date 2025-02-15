package com.mammba.APIManager.Services.Security;

import com.mammba.APIManager.Model.Users;
import com.mammba.APIManager.Repository.UsersTable;

import java.util.regex.Pattern;

public class EmailValidate {

    public static boolean isValid(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern p = Pattern.compile(emailRegex);

        return email != null && p.matcher(email).matches();
    }

    public static boolean exists(String email){
        Users user = UsersTable.getUserByEmail(email);
        return !(user.getName()== null);
    }

}