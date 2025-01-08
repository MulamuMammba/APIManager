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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Authenticator {

    @GetMapping("/")
    public ResponseEntity<Object> Test() {

        String id = Generator.generateApiId();
        String email = "hello@gmail.com";
        Database.createAccountTable();
        Database.createApiTable();
        Database.createEndpointsTable();
        EndpointsTable.insertEndpoint(new Endpoints("2",id,"mammba", "mhs/ds","post"));
        UsersTable.createUser(new Users(email, null, "Muthu Muthu", null, null, null));
        ApiTable.insertApi(new API(id,email,"Mr Api", null, "Https://hello.com/"));


        Map<String, String> data = new HashMap<>();
        data.put("Response From Users Table", UsersTable.getUserByEmail("hello@gmail.com").getName());
        data.put("Response From API Table", ApiTable.getApiById(id).getBaseUrl());
        data.put("Response From Endpoints Table",EndpointsTable.getEndpointById("2").getName());
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
