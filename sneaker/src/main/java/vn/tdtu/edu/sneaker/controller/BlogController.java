package vn.tdtu.edu.sneaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/blog")
public class BlogController {
    @GetMapping("/")
    public String Index(){
        return "blog";
    }

    @GetMapping("/blog-single/{id}")
    public String goSingleBlog(@PathVariable("id") Long blogId){
//        Code here
        return "blog-single";
    }
}
