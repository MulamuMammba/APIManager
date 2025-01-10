package com.mammba.APIManager.Controller.Auth;

import com.mammba.APIManager.Services.EmailValidate;
import com.mammba.APIManager.Services.Usecase.UsersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    Authentication auth = new Authentication();

    @GetMapping("/register/{email}/{password}/{name}/{surname}")
    public ResponseEntity<Object> Register(@PathVariable String email, @PathVariable String password, @PathVariable String name, @PathVariable String surname){

        if (!EmailValidate.isValid(email)) {
            return new ResponseEntity<>("Invalid email address", HttpStatus.FORBIDDEN);
        } else if(EmailValidate.exists(email)){
            return new ResponseEntity<>("User with email already exists", HttpStatus.FORBIDDEN);
        }else {
            UsersUseCase.createUser(email,password,name,surname);
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }
    }
}
