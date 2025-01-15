package com.mammba.APIManager.Controller.Auth;

import com.mammba.APIManager.Model.DTO.LoginRequest;
import com.mammba.APIManager.Model.Users;
import com.mammba.APIManager.Repository.UsersTable;
import com.mammba.APIManager.Services.Security.EmailValidate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginController {

    Authentication auth = new Authentication();

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (!EmailValidate.isValid(email)) {
            return new ResponseEntity<>("Invalid email address", HttpStatus.FORBIDDEN);
        } else {
            Users user = UsersTable.getUserByEmail(email);

            if (user.getName() == null) {
                return new ResponseEntity<>("Incorrect Password or Email", HttpStatus.NOT_FOUND);
            } else {
                if (auth.login(email, password)) {
                    return new ResponseEntity<>(Map.of("token", email), HttpStatus.OK);
                } else return new ResponseEntity<>("Incorrect Password or Email", HttpStatus.UNAUTHORIZED);

            }
        }
    }
}