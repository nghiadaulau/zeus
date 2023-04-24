package vn.tdtu.edu.sneaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.tdtu.edu.commons.service.implement.CartServiceImpl;
import vn.tdtu.edu.commons.service.implement.CategoryServiceImpl;
import vn.tdtu.edu.commons.service.implement.ProductServiceImpl;

@Controller
@RequestMapping("/")
public class DefaultController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    CategoryServiceImpl categoryService;

    @RequestMapping("/")
    public String mainPage() {
        return "redirect:/home-page";
    }

    @GetMapping("/home-page")
    public String Index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("products", productService.getAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("get4Products", productService.get4ProductsByCategoryId(categoryService.findAll()));

        if (authentication.getAuthorities().toString().equals("[ROLE_ANONYMOUS]")) {
            model.addAttribute("recommended", productService.findRandomProducts());
        } else {
            model.addAttribute("username", authentication.getName());
            model.addAttribute("recommended", productService.findRandomProducts());
        }
        return "index";
    }
}