package com.mammba.APIManager.Controller.EndPoints;

import com.mammba.APIManager.Controller.Auth.Authentication;
import com.mammba.APIManager.Model.Endpoints;
import com.mammba.APIManager.Model.Response;
import com.mammba.APIManager.Services.Security.EmailValidate;
import com.mammba.APIManager.Services.Usecase.ApiUseCase;
import com.mammba.APIManager.Services.Usecase.EndPointUseCase;
import com.mammba.APIManager.Services.Usecase.TesterUseCase;
import com.mammba.APIManager.Services.Usecase.UsersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EndPointsController {

    Authentication auth = new Authentication();

    @PostMapping("/api/v1/endpoint/list")
    public ResponseEntity<Object> ApiList(@RequestBody Map<String, String> requestBody) {

        String email = requestBody.get("userEmail");
        String ApiId = requestBody.get("ApiId");

        if (!EmailValidate.isValid(email)) {
            return new ResponseEntity<>("Invalid Password or Email", HttpStatus.BAD_REQUEST);

        } else if (!UsersUseCase.UserExists(email) || email == null || ApiId == null) {
            return new ResponseEntity<>("User doesn't exist or doesn't have permission", HttpStatus.FORBIDDEN);
        } else {
            List<Endpoints> endpoints = EndPointUseCase.EndPointList(ApiId);
            return new ResponseEntity<>(endpoints, HttpStatus.OK);
        }
    }

    @PostMapping("/api/v1/endpoint/test")
    public ResponseEntity<Object> TestEndpoint(@RequestBody Map<String,String> requestBody){

        Response data = TesterUseCase.TestEndpoint(requestBody.get("id"));
        System.out.println(data.getBody());
        return new ResponseEntity<>(data.getBody(), HttpStatus.OK);
    }
    @PostMapping("/api/v1/endpoint/create")
    public ResponseEntity<Object> CreateEndpoint(@RequestBody Map<String, String> requestBody) {

        System.out.println(requestBody);
        String email = requestBody.get("userEmail");
        Endpoints endpoint = new Endpoints("0", requestBody.get("apiId"), requestBody.get("name"), requestBody.get("url"), requestBody.get("method"));

        System.out.println(endpoint);
        if (!EmailValidate.isValid(email)) {
            return new ResponseEntity<>("Invalid Email", HttpStatus.BAD_REQUEST);

        } else if (!UsersUseCase.UserExists(email)) {
            return new ResponseEntity<>("User doesn't exist or doesn't have permission", HttpStatus.FORBIDDEN);
        } else {
            EndPointUseCase.CreateEndPoint(endpoint);
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        }

    }

    @GetMapping("/api/v1/endpoint/remove")
    public ResponseEntity<Object> RemoveEndpoint(@RequestBody Endpoints endpoint) {
        String email = ApiUseCase.FetchApi(endpoint.getApiId()).getUserEmail();
        if (!EmailValidate.isValid(email)) {
            return new ResponseEntity<>("Invalid Email", HttpStatus.BAD_REQUEST);
        } else if (!UsersUseCase.UserExists(email)) {
            return new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);

        }
        else {
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        }

    }

}
