package vn.tdtu.edu.sneaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.tdtu.edu.commons.dto.ProductDTO;
import vn.tdtu.edu.commons.model.Category;
import vn.tdtu.edu.commons.model.Product;
import vn.tdtu.edu.commons.service.implement.CategoryServiceImpl;
import vn.tdtu.edu.commons.service.implement.ProductServiceImpl;

import java.util.ArrayList;
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
        model.addAttribute("all_product", productService.findAll());

        int pages = productService.getAllProducts().size() / 12;
        List<Integer> pagesNo = new ArrayList<>();

        for (int i = 0; i < pages; i++) {
            pagesNo.add(i + 1);
        }

        Map<Integer, Page<ProductDTO>> pageNoWithSpecificProducts = new HashMap<>();

        for (int i = 0; i < pages; i++) {
            pageNoWithSpecificProducts.put(i+1, productService.pageProducts(i));
        }

        model.addAttribute("pagesQuantity", pagesNo);
        model.addAttribute("pages", pageNoWithSpecificProducts);

        return "shop";
    }
}
