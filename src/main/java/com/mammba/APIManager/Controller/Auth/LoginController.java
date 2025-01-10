package com.mammba.APIManager.Controller.Auth;

import com.mammba.APIManager.Model.Users;
import com.mammba.APIManager.Repository.UsersTable;
import com.mammba.APIManager.Services.EmailValidate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    Authentication auth = new Authentication();

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<Object> login(@PathVariable String email, @PathVariable String password) {

        if (!EmailValidate.isValid(email)) {
            return new ResponseEntity<>("Invalid email address", HttpStatus.FORBIDDEN);
        } else {
            Users user = UsersTable.getUserByEmail(email);

            if (user.getName() == null) {
                return new ResponseEntity<>("User with the Email not found", HttpStatus.NOT_FOUND);
            } else {
                if (auth.login(email, password)) {
                    Map<String, Users> data = new HashMap<>();
                    data.put("User", user);
                    return new ResponseEntity<>(data, HttpStatus.OK);
                } else return new ResponseEntity<>("Invalid Password or Email", HttpStatus.UNAUTHORIZED);

            }
        }
    }
}