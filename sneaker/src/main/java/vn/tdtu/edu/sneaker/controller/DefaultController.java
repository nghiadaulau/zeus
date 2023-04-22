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

        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                // Handle case login but user is anonymous
                if (authentication.getName().equals("anonymousUser")) {
                    System.out.println("Anonymous User");
//                    return "redirect:/auth/login";
                    // DEBUG
                    model.addAttribute("username", authentication.getName());
                    model.addAttribute("prods", productService.findAll());
                    model.addAttribute("categories", categoryService.findAll());

                    return "index";
                }
                System.out.println("User logged in");
                model.addAttribute("username", authentication.getName());
                model.addAttribute("prods", productService.findAll());
                model.addAttribute("categories", categoryService.findAll());

                return "index";
            } else {
                System.out.println("User not logged in");

//                return "redirect:/auth/login";
                // DEBUG
                return "index";
            }
        }
        System.out.println("Null Authentication");

        return "redirect:/auth/login";
    }
}