package com.mammba.APIManager.Controller.Auth;

import com.mammba.APIManager.Model.Users;
import com.mammba.APIManager.Services.EmailValidate;
import com.mammba.APIManager.Services.Usecase.UsersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    Authentication auth = new Authentication();

    @PostMapping("/api/auth/register")
    public ResponseEntity<Object> Register(@RequestBody Users user) {

        if (!EmailValidate.isValid(user.getEmail())) {
            return new ResponseEntity<>("Invalid email address", HttpStatus.OK);
        } else if (EmailValidate.exists(user.getEmail())) {
            return new ResponseEntity<>("User with email already exists", HttpStatus.OK);
        } else {
            UsersUseCase.createUser(user);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }
    /*
    *  @PostMapping("/api/auth/register")
    public ResponseEntity<Object> register(
            @RequestBody UserRegistrationRequest request) {

        if (!EmailValidate.isValid(request.getEmail())) {
            return new ResponseEntity<>("Invalid email address", HttpStatus.FORBIDDEN);
        } else if (EmailValidate.exists(request.getEmail())) {
            return new ResponseEntity<>("User with email already exists", HttpStatus.FORBIDDEN);
        } else {
            UsersUseCase.createUser(
                request.getEmail(),
                request.getPassword(),
                request.getName(),
                request.getSurname(),
                request.getProfession(),
                request.getPhoneNum()
            );
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }
    * */
}
