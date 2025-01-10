package com.mammba.APIManager.Controller.EndPoints;

import com.mammba.APIManager.Controller.Auth.Authentication;
import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Services.EmailValidate;
import com.mammba.APIManager.Services.Usecase.ApiUseCase;
import com.mammba.APIManager.Services.Usecase.EndPointUseCase;
import com.mammba.APIManager.Services.Usecase.UsersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EndPointsController {

    Authentication auth = new Authentication();

    @GetMapping("/endpoint/list/{email}")
    public ResponseEntity<Object> ApiList(@PathVariable String email) {

        if (EmailValidate.isValid(email)) {
            List<API> api = ApiUseCase.apiList(email);
            return new ResponseEntity<>(api, HttpStatus.OK);
        } else return new ResponseEntity<>("Invalid Password or Email", HttpStatus.OK);

    }

    @GetMapping("/endpoint/create/{email}/{name}")
    public ResponseEntity<Object> CreateApi(@PathVariable String email, @PathVariable String name) {

        if (!EmailValidate.isValid(email)) {
            return new ResponseEntity<>("Invalid Password or Email", HttpStatus.OK);

        } else {
            ApiUseCase.CreateApi(email, name);
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        }

    }

    @GetMapping("/endpoint/remove/{email}/{endPointId}")
    public ResponseEntity<Object> RemoveApi(@PathVariable String email, @PathVariable String endPointId) {

        if (!EmailValidate.isValid(email)) {
            return new ResponseEntity<>("Invalid Email", HttpStatus.OK);
        } else if (!UsersUseCase.UserExists(email)) {
            return new ResponseEntity<>("User does not exist", HttpStatus.OK);

        } else if (!EndPointUseCase.EpiExists(endPointId)) {
            return new ResponseEntity<>("Api Does not exist", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        }

    }
}
