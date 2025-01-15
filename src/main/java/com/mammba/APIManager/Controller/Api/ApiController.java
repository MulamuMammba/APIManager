package com.mammba.APIManager.Controller.Api;

import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Services.Security.EmailValidate;
import com.mammba.APIManager.Services.Usecase.ApiUseCase;
import com.mammba.APIManager.Services.Usecase.UsersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ApiController {

    @PostMapping("/api/v1/api/list")
    public ResponseEntity<Object> ApiList(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");

        if (email == null || !EmailValidate.isValid(email)) {
            return new ResponseEntity<>("Invalid email", HttpStatus.BAD_REQUEST);
        }

        if (!UsersUseCase.UserExists(email)) {
            return new ResponseEntity<>("User doesn't exist or doesn't have permission", HttpStatus.FORBIDDEN);
        } else {

            List<API> api = ApiUseCase.apiList(email);
            return new ResponseEntity<>(api, HttpStatus.OK);
        }
    }

    // POST endpoint to create an API
    @PostMapping("/api/v1/api/create")
    public ResponseEntity<Object> CreateApi(@RequestBody API api) {
        String userEmail = api.getUserEmail(); // Extract email from API object

        if (!EmailValidate.isValid(userEmail)) {
            return new ResponseEntity<>("Invalid Email", HttpStatus.BAD_REQUEST);
        } else if (!UsersUseCase.UserExists(userEmail)) {
            return new ResponseEntity<>("User doesn't have permission to create API", HttpStatus.FORBIDDEN);
        } else {
            ApiUseCase.CreateApi(api);
            return new ResponseEntity<>("API created successfully", HttpStatus.OK);
        }
    }

    // POST endpoint to remove an API
    @PostMapping("/api/v1/api/remove")
    public ResponseEntity<Object> RemoveApi(@RequestBody API api) {
        String userEmail = api.getUserEmail(); // Extract email from API object

        if (!UsersUseCase.UserExists(userEmail)) {
            return new ResponseEntity<>("User does not exist", HttpStatus.FORBIDDEN);
        } else {
            ApiUseCase.RemoveApi(api.getId()); // Remove API
            return new ResponseEntity<>("API removed successfully", HttpStatus.OK);
        }
    }
}
