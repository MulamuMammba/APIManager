package com.mammba.APIManager.Controller.Auth;

import com.mammba.APIManager.Model.Users;
import com.mammba.APIManager.Repository.UsersTable;
import com.mammba.APIManager.Services.EmailValidate;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;


@Controller
public class AuthenticateController {

    Authentication auth = new Authentication();

    @GetMapping("/")
    public ResponseEntity<String> index() {

        return new ResponseEntity<>("Its Running", HttpStatus.OK);

    }

    @GetMapping("/user/{email}")
    public ResponseEntity<Object> Test(@PathVariable String email) {

        if (EmailValidate.isValid(email)){
            Users user = UsersTable.getUserByEmail(email);

            if (user.getName() == null){
                return new ResponseEntity<>("User with the Email not found", HttpStatus.OK);
            }else {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }

        }else {
            return new ResponseEntity<>("Invalid email address", HttpStatus.OK);
        }

    }

}
