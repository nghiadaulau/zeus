package vn.tdtu.edu.sneaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/check-out")
@Controller
public class CheckOutController {
    @GetMapping("/")
    public String Index(){
        return "checkout";
    }
}
