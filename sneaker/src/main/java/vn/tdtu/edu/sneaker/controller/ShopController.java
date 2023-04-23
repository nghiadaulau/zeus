package vn.tdtu.edu.sneaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.tdtu.edu.commons.model.Category;
import vn.tdtu.edu.commons.model.Product;
import vn.tdtu.edu.commons.service.implement.CategoryServiceImpl;
import vn.tdtu.edu.commons.service.implement.ProductServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/")
    public String Index(Model model) {
        model.addAttribute("categories", categoryService.findAll());

        Map<Long, List<Product>> products = new HashMap<>();

        for (Category category : categoryService.findAll()) {
            products.put(category.getId(), productService.getProductsInCategory(category.getId()));
        }

        model.addAttribute("products", products);
        model.addAttribute("all_product", productService.getAllProducts());
        System.out.println(productService.findAll().size());

        return "shop";
    }
}
