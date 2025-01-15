package com.mammba.APIManager.Controller.Auth;

import com.mammba.APIManager.Model.Users;
import com.mammba.APIManager.Repository.UsersTable;
import com.mammba.APIManager.Services.Security.EmailValidate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Authenticator {

    @GetMapping("/test2/{api}")
    public ResponseEntity<Object> Test2(@PathVariable String email) {


            Users user = UsersTable.getUserByEmail(email);

            if (user.getName() == null){
                return new ResponseEntity<>("User with the Email not found", HttpStatus.OK);
            }else {
                Map<String, String> data = new HashMap<>();
                data.put("email", user.getEmail());
                data.put("name", user.getName());
                return new ResponseEntity<>(data, HttpStatus.OK);
            }


    }
    @GetMapping("/test3/{endpoint}")
    public ResponseEntity<Object> Test3(@PathVariable String email) {

        if (EmailValidate.isValid(email)){
            Users user = UsersTable.getUserByEmail(email);

            if (user.getName() == null){
                return new ResponseEntity<>("User with the Email not found", HttpStatus.OK);
            }else {
                Map<String, String> data = new HashMap<>();
                data.put("email", user.getEmail());
                data.put("name", user.getName());
                return new ResponseEntity<>(data, HttpStatus.OK);
            }

        }else {
            return new ResponseEntity<>("Invalid endpoint address", HttpStatus.OK);
        }

    }

}
