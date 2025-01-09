package com.mammba.APIManager.Controller.Auth;

import com.mammba.APIManager.Model.Users;
import com.mammba.APIManager.Repository.Database;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
public class AuthenticateController {

    Authentication auth = new Authentication();
// @GetMapping ("/")
//    public String index(HttpSession session)
//{
//    Database.createAccountTable();
//    Database.createApiTable();
//    Database.createEndpointsTable();
//    Users user = (Users) session.getAttribute("currentUser");
//
//    if (user!= null){
//        session.removeAttribute("currentUser");
//    }
//    return "index";
//}

    @GetMapping ("/")
    public String index()
    {
        Database.createAccountTable();
        Database.createApiTable();
        Database.createEndpointsTable();
//        Users user = (Users) session.getAttribute("currentUser");
//
//        if (user!= null){
//            session.removeAttribute("currentUser");
//        }
        return "index";
    }

@GetMapping("/register")
    public String register(){

    return "register";
}
@PostMapping("/register")
public String register(
        @RequestParam("username") String email,
        @RequestParam("surname") String password,
        @RequestParam("password") String name,
        @RequestParam("password") String surname,
        @RequestParam("password") String profession,
        @RequestParam("password") String phoneNum,
        Model model
)
{
    Users user = new Users(email, password,name, surname, profession,phoneNum);

boolean response = auth.register(user);

    return "login";

}

@GetMapping("/login")
    public String login(){
    return "login";
}

@PostMapping("/login")
public String login(
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        Model model,
        HttpSession session

){

    return auth.login(email,password,session,model);
}
@GetMapping("/surname")
    public String surname(){
        return "surname";
    }

}
