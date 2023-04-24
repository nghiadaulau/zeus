package vn.tdtu.edu.sneaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.tdtu.edu.commons.dto.ProductDTO;
import vn.tdtu.edu.commons.model.Category;
import vn.tdtu.edu.commons.model.Product;
import vn.tdtu.edu.commons.service.implement.BrandServiceImpl;
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
    @Autowired
    BrandServiceImpl brandService;

    @GetMapping("/")
    public String Index(Model model) {
        model.addAttribute("categories", categoryService.findAll());

        Map<Long, List<Product>> products = new HashMap<>();
        Map<Long, Page<ProductDTO>> pageNoWithSpecificProductsForPerCategory = new HashMap<>();
        Map<Category, Map<Integer, List<Product>>> pagesNoForPerCategory = new HashMap<>();

        for (Category category : categoryService.findAll()) {
            products.put(category.getId(), productService.getProductsInCategory(category.getId()));

            int prodsInAPage = 12;
            int productsCount = productService.getProductsInCategory(category.getId()).size();
            int pagesNum = (int) Math.ceil((double) productsCount / prodsInAPage);

            for (int i = 0; i < pagesNum; i++) {
                List<Product> products1 = productService
                        .getProductsForPerCategoryByCategoryId(category, i, prodsInAPage);
                List<Product> products2 = new ArrayList<>(products1);
                Map<Integer, List<Product>> innerMap = pagesNoForPerCategory.computeIfAbsent(category, k -> new HashMap<>());
                innerMap.put(i + 1, products2);
                pagesNoForPerCategory.put(category, innerMap);
            }
        }

        model.addAttribute("products", products);
        model.addAttribute("all_product", productService.findAll());
        model.addAttribute("brands", brandService.findAllByActivated());
        model.addAttribute("all_product_size", productService.getAll().size());
        model.addAttribute("pages_no_for_per_category", pagesNoForPerCategory);

        int prodsInAPage = 12;
        int pages = productService.getAllProducts().size() / prodsInAPage;

        List<Integer> pagesNo = new ArrayList<>();

        for (int i = 0; i < pages; i++) {
            pagesNo.add(i + 1);
        }

        Map<Integer, Page<ProductDTO>> pageNoWithSpecificProducts = new HashMap<>();

        for (int i = 0; i < pages; i++) {
            pageNoWithSpecificProducts.put(i + 1, productService.pageProducts(i));
        }
        model.addAttribute("pagesQuantity", pagesNo);
        model.addAttribute("pages", pageNoWithSpecificProducts);
        model.addAttribute("pagesQuantityForPerCategory", pagesNoForPerCategory);
        model.addAttribute("pagesForPerCategory", pageNoWithSpecificProductsForPerCategory);

        return "shop";
    }
    @GetMapping("/search")
    public String search(@RequestParam String keySearch, Model model){
        model.addAttribute("categories", categoryService.findAll());
        Map<Long, List<Product>> products = new HashMap<>();
        Map<Long, Page<ProductDTO>> pageNoWithSpecificProductsForPerCategory = new HashMap<>();
        Map<Category, Map<Integer, List<Product>>> pagesNoForPerCategory = new HashMap<>();

        for (Category category : categoryService.findAll()) {
            products.put(category.getId(), productService.getProductsInCategory(category.getId()));
            int prodsInAPage = 12;
            int productsCount = productService.getProductsInCategory(category.getId()).size();
            int pagesNum = (int) Math.ceil((double) productsCount / prodsInAPage);

            for (int i = 0; i < pagesNum; i++) {
                List<Product> products1 = productService
                        .getProductsForPerCategoryByCategoryId(category, i, prodsInAPage);
                List<Product> products2 = new ArrayList<>(products1);
                Map<Integer, List<Product>> innerMap = pagesNoForPerCategory.computeIfAbsent(category, k -> new HashMap<>());
                innerMap.put(i + 1, products2);
                pagesNoForPerCategory.put(category, innerMap);
            }
        }
        model.addAttribute("products", products);
        model.addAttribute("all_product", productService.findAll());
        model.addAttribute("brands", brandService.findAllByActivated());
        model.addAttribute("all_product_size", products.size());
        model.addAttribute("pages_no_for_per_category", pagesNoForPerCategory);

        int prodsInAPage = 12;
        int pages = productService.getAllProducts().size() / prodsInAPage;

        List<Integer> pagesNo = new ArrayList<>();

        for (int i = 0; i < pages; i++) {
            pagesNo.add(i + 1);
        }

        Map<Integer, Page<ProductDTO>> pageNoWithSpecificProducts = new HashMap<>();

        for (int i = 0; i < pages; i++) {
            pageNoWithSpecificProducts.put(i + 1, productService.pageProducts(i));
        }
        model.addAttribute("pagesQuantity", pagesNo);
        model.addAttribute("pages", pageNoWithSpecificProducts);
        model.addAttribute("pagesQuantityForPerCategory", pagesNoForPerCategory);
        model.addAttribute("pagesForPerCategory", pageNoWithSpecificProductsForPerCategory);

        return "shop";
    }
}
