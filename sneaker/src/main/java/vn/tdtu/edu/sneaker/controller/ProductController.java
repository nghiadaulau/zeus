package vn.tdtu.edu.sneaker.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.tdtu.edu.commons.service.implement.ProductServiceImpl;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
<<<<<<< Updated upstream
    private ProductServiceImpl productService;
    @GetMapping("/")
    public String Index(Model model){
        model.addAttribute("products",productService.findAll());
=======
    ProductServiceImpl productService;

    @GetMapping("/")
    public String Index(Model model){
        model.addAttribute("prods", productService.findAll());

>>>>>>> Stashed changes
        return "index";
    }
    @GetMapping("/product-details/{id}")
    public String goProductDetails(@PathVariable("id") Long id){
        return "product-details";
    }
//    Get Params: /search?keySearch =...
    @GetMapping("/search")
    public String goSearch(@RequestParam String keySearch){
        productService.searchProductsCus(0,keySearch);
        return "index";
    }
}
