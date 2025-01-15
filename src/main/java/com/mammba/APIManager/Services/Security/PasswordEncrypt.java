package com.mammba.APIManager.Services.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncrypt {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public String EncryptPassword(String rawPassword){

        return passwordEncoder.encode(rawPassword);
    }

    public boolean PasswordMatch(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword, encodedPassword);

    }

}
