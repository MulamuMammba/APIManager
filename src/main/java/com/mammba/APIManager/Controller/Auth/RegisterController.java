package com.mammba.APIManager.Controller.Auth;

import com.mammba.APIManager.Model.Users;
import com.mammba.APIManager.Services.Security.EmailValidate;
import com.mammba.APIManager.Services.Usecase.UsersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RegisterController {

    Authentication auth = new Authentication();

    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<Object> Register(@RequestBody Users user) {

        System.out.println(user);
        if (!EmailValidate.isValid(user.getEmail())) {
            return new ResponseEntity<>("Invalid email address", HttpStatus.FORBIDDEN);
        } else if (EmailValidate.exists(user.getEmail())) {
            return new ResponseEntity<>("User with email already exists", HttpStatus.FORBIDDEN);
        } else {
            UsersUseCase.createUser(user);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }

}
