package vn.tdtu.edu.sneaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.tdtu.edu.commons.dto.CustomerDTO;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/")
    public String Index(Model model){

        return "information";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("customerDTO",new CustomerDTO());
        return "login";
    }
    @PostMapping("/login")
    public String login(){
//        Process post login here
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("customerDTO",new CustomerDTO());
        return "register";
    }
    @PostMapping("/register")
    public String register(){
//        Process post register here
        return "register";
    }
}
