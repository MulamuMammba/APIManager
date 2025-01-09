package com.mammba.APIManager.Controller;

import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Model.Endpoints;
import com.mammba.APIManager.Model.Users;
import com.mammba.APIManager.Repository.ApiTable;
import com.mammba.APIManager.Repository.Database;
import com.mammba.APIManager.Repository.EndpointsTable;
import com.mammba.APIManager.Repository.UsersTable;
import com.mammba.APIManager.Services.EmailValidate;
import com.mammba.APIManager.Services.Generator;
import com.mammba.APIManager.Services.PasswordEncrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Authenticator {

    @GetMapping("/2")
    public ResponseEntity<Object> Test() {
        String email = "hel5o@gmail.com";
        PasswordEncrypt pe = new PasswordEncrypt();
       UsersTable.createUser(new Users(email, pe.EncryptPassword("hello its me"), "Muthu Muthu", null, null, null));
boolean test = pe.PasswordMatch("hello its me",UsersTable.getUserByEmail("hel5o@gmail.com").getPassword());

        Map<String, String> data = new HashMap<>();
        data.put("Response From Users Table", UsersTable.getUserByEmail("hel5o@gmail.com").getPassword());
        data.put("Response From Endpoints Table",EndpointsTable.getEndpointById("5").getName());
        data.put("Does it match : ", Boolean.toString(test));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/test/{email}")
    public ResponseEntity<Object> Test(@PathVariable String email) {

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
            return new ResponseEntity<>("Invalid email address", HttpStatus.OK);
        }

    }
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
