package vn.tdtu.edu.sneaker.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/")
    public String Index(){
        return "index";
    }
    @GetMapping("/product-details/{id}")
    public String goProductDetails(@PathVariable("id") Long id){
        return "product-details";
    }
//    Get Params: /search?keySearch =...
    @GetMapping("/search")
    public String goSearch(@RequestParam String keySearch){
        return "index";
    }
}
