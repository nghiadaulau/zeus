package vn.tdtu.edu.sneaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.tdtu.edu.commons.service.implement.ProductServiceImpl;

@Controller
@RequestMapping("/shop")
public class ShopController {


    @GetMapping("/")
    public String Index() {
        return "shop";
    }
}
