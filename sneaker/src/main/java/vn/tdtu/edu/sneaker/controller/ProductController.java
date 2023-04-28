package vn.tdtu.edu.sneaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.tdtu.edu.commons.dto.ProductDTO;
import vn.tdtu.edu.commons.model.Category;
import vn.tdtu.edu.commons.model.Product;
import vn.tdtu.edu.commons.service.implement.BrandServiceImpl;
import vn.tdtu.edu.commons.service.implement.CategoryServiceImpl;
import vn.tdtu.edu.commons.service.implement.ProductServiceImpl;

import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    BrandServiceImpl brandService;

    @GetMapping("/")
    public String Index(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping("/product-details/{id}")
    public String goProductDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("recommended",
                productService.findRandomProductsByBrandAndCategory(productService.getProductById(id)));

        return "product-details";
    }

    //    Get Params: /search?keySearch =...
//    @GetMapping("/search")
//    public String goSearch(@RequestParam(name="keySearch", required = false) String keySearch) {
//        if (Objects.equals(keySearch, "")) {
////            return "redirect:/shop/";
//            // Should be alert something
//        } else {
//            for (ProductDTO product : productService.searchProductsCus(0, keySearch)) {
//                System.out.println(product.getId());
//            }
//        }
//
//        return "redirect:/shop/search";
//    }
}
