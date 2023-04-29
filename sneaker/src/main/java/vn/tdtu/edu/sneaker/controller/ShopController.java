package vn.tdtu.edu.sneaker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.tdtu.edu.commons.dto.ProductDTO;
import vn.tdtu.edu.commons.model.*;
import vn.tdtu.edu.commons.service.implement.BrandServiceImpl;
import vn.tdtu.edu.commons.service.implement.CategoryServiceImpl;
import vn.tdtu.edu.commons.service.implement.ProductServiceImpl;

import java.util.*;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    BrandServiceImpl brandService;

    List<Product> products = new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    List<Brand> brands = new ArrayList<>();

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "brand", required = false) Long brand_id,
                        @RequestParam(name = "category", required = false) Long category_id,
                        @RequestParam(name = "sortBy", required = false) String sortBy) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getAuthorities().toString().equals("[ROLE_ANONYMOUS]")) {
            model.addAttribute("username", authentication.getName());
        }
        return "redirect:/shop/filter?brand=0&category=0";
    }

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(name = "sortBy", required = false) String sortBy,
                         @RequestParam(name = "keySearch", required = false) String keySearch) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getAuthorities().toString().equals("[ROLE_ANONYMOUS]")) {
            model.addAttribute("username", authentication.getName());
        }
        if (Objects.equals(keySearch, "")) {
            // Should be alert something
        }

        products.clear();

        int prodsInAPage = 12;
        int pages = (int) Math.ceil((double) productService.search(keySearch).size() / prodsInAPage);

        List<Integer> pagesNo = new ArrayList<>();

        for (int i = 0; i < pages; i++) {
            pagesNo.add(i + 1);
        }

        Map<Integer, Page<ProductDTO>> pageNoWithSpecificProducts = new HashMap<>();

        if (sortBy != null) {
            for (int i = 0; i < pages; i++) {
                if (sortBy.equals("filterByDesc")) {
                    pageNoWithSpecificProducts.put(i + 1, productService.searchProductsCus(i, keySearch, "filterByDesc"));
                }
                if (sortBy.equals("filterByAsc")) {
                    pageNoWithSpecificProducts.put(i + 1, productService.searchProductsCus(i, keySearch, "filterByAsc"));
                }
            }
        } else {
            for (int i = 0; i < pages; i++) {
                pageNoWithSpecificProducts.put(i + 1, productService.searchProductsCus(i, keySearch, null));
            }
        }


        model.addAttribute("pagesQuantity", pagesNo);
        model.addAttribute("pages", pageNoWithSpecificProducts);

        return "search";
    }

    @GetMapping("/filter")
    public String filter(Model model,
                         @RequestParam(name = "brand", required = false) Long brand_id,
                         @RequestParam(name = "category", required = false) Long category_id,
                         @RequestParam(name = "sortBy", required = false) String sortBy) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getAuthorities().toString().equals("[ROLE_ANONYMOUS]")) {
            model.addAttribute("username", authentication.getName());
        }
        categories = new ArrayList<>(categoryService.findAll());
        brands = new ArrayList<>(brandService.findAllByActivated());

        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);

        products.clear();

        int prodsInAPage = 12;
        int productsCount;

        if (brand_id == 0 && category_id == 0) {
            productsCount = productService.getAll().size();
        } else if (brand_id == 0) {
            productsCount = productService.getProductsInCategory(category_id).size();
        } else if (category_id == 0) {
            productsCount = productService.getProductsInBrand(brand_id).size();
        } else {
            productsCount = productService.getProductsInBrandAndCategory(category_id, brand_id).size();
        }

        int pages = (int) Math.ceil((double) productsCount / prodsInAPage);

        List<Integer> pagesNo = new ArrayList<>();

        for (int i = 0; i < pages; i++) {
            pagesNo.add(i + 1);
        }

        Map<Integer, Page<ProductDTO>> pageNoWithSpecificProducts = new HashMap<>();

        // Check null
        if (sortBy != null) {
            if (category_id != null) {
                if (brand_id == 0 && category_id == 0) {
                    for (int i = 0; i < pages; i++) {
                        if (sortBy.equals("filterByDesc")) {
                            pageNoWithSpecificProducts.put(i + 1,
                                    productService.getProductsByConditions(i, category_id, brand_id, "filterByDesc"));
                        }
                        if (sortBy.equals("filterByAsc")) {
                            pageNoWithSpecificProducts.put(i + 1,
                                    productService.getProductsByConditions(i, category_id, brand_id, "filterByAsc"));
                        }
                    }
                } else if (brand_id == 0) {
                    for (int i = 0; i < pages; i++) {
                        if (sortBy.equals("filterByDesc")) {
                            pageNoWithSpecificProducts.put(i + 1,
                                    productService.getProductsByConditions(i, category_id, null, "filterByDesc"));
                        }
                        if (sortBy.equals("filterByAsc")) {
                            pageNoWithSpecificProducts.put(i + 1,
                                    productService.getProductsByConditions(i, category_id, null, "filterByAsc"));
                        }
                    }
                } else if (category_id == 0) {
                    for (int i = 0; i < pages; i++) {
                        if (sortBy.equals("filterByDesc")) {
                            pageNoWithSpecificProducts.put(i + 1,
                                    productService.getProductsByConditions(i, null, brand_id, "filterByDesc"));
                        }
                        if (sortBy.equals("filterByAsc")) {
                            pageNoWithSpecificProducts.put(i + 1,
                                    productService.getProductsByConditions(i, null, brand_id, "filterByAsc"));
                        }
                    }
                } else {
                    for (int i = 0; i < pages; i++) {
                        if (sortBy.equals("filterByDesc")) {
                            pageNoWithSpecificProducts.put(i + 1,
                                    productService.getProductsByConditions(i, category_id, brand_id, "filterByDesc"));
                        }
                        if (sortBy.equals("filterByAsc")) {
                            pageNoWithSpecificProducts.put(i + 1,
                                    productService.getProductsByConditions(i, category_id, brand_id, "filterByAsc"));
                        }
                    }
                }
            }
            if (category_id == null) {
                for (int i = 0; i < pages; i++) {
                    if (sortBy.equals("filterByDesc")) {
                        pageNoWithSpecificProducts.put(i + 1,
                                productService.getProductsByConditions(i, null, brand_id, "filterByDesc"));
                    }
                    if (sortBy.equals("filterByAsc")) {
                        pageNoWithSpecificProducts.put(i + 1,
                                productService.getProductsByConditions(i, null, brand_id, "filterByAsc"));
                    }
                }
            }
        } else {
            if (category_id != null) {
                if (brand_id == 0 && category_id == 0) {
                    for (int i = 0; i < pages; i++) {
                        pageNoWithSpecificProducts.put(i + 1,
                                productService.getProductsByConditions(i, category_id, brand_id, null));
                    }
                } else if (brand_id == 0) {
                    for (int i = 0; i < pages; i++) {
                        pageNoWithSpecificProducts.put(i + 1,
                                productService.getProductsByConditions(i, category_id, null, null));
                    }
                } else if (category_id == 0) {
                    for (int i = 0; i < pages; i++) {
                        pageNoWithSpecificProducts.put(i + 1,
                                productService.getProductsByConditions(i, null, brand_id, null));
                    }
                } else {
                    for (int i = 0; i < pages; i++) {
                        pageNoWithSpecificProducts.put(i + 1,
                                productService.getProductsByConditions(i, category_id, brand_id, null));
                    }
                }
            }
            if (category_id == null) {
                for (int i = 0; i < pages; i++) {
                    pageNoWithSpecificProducts.put(i + 1,
                            productService.getProductsByConditions(i, null, brand_id, null));
                }
            }
        }

        model.addAttribute("all_product", products);
        model.addAttribute("pagesQuantity", pagesNo);
        model.addAttribute("pages", pageNoWithSpecificProducts);
        return "shop";
    }
}
