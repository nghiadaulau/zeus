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
        Map<Long, Integer> pagesNoForPerCategory = new HashMap<>();
//        System.out.println(productService.getProductsForPerCategoryByCategoryId(categoryService.findAll()));

        for (Map.Entry<Long, List<Product>> entry :
                productService.getProductsForPerCategoryByCategoryId(categoryService.findAll()).entrySet()) {
            System.out.printf("Category %d: %d products\n", entry.getKey(), entry.getValue().size());
        }

        for (Category category : categoryService.findAll()) {
            products.put(category.getId(), productService.getProductsInCategory(category.getId()));

//            System.out.printf("Category %d have %d products\n", category.getId(),
//                    productService.getProductsInCategory(category.getId()).size());
//
//            for (Product product : productService.getProductsInCategory(category.getId())) {
//                System.out.printf("Category %d have product with id=%d\n", category.getId(),
//                        product.getId());
//            }

//            int pageNo = 0; // Reset pageNo to 0 for each category
//            Long categoryId = category.getId();
//            List<Product> productsInCategory = productService.getProductsInCategory(categoryId);
//
//            for (Product product : productService.getProductsInCategory(categoryId)) {
//                System.out.printf("Product id=%d in category %d\n", product.getId(), categoryId);
//            }
//
//            int numProducts = productsInCategory.size();
//            int prodsInAPage = 12;
//            int pages = (numProducts + prodsInAPage - 1) / prodsInAPage;
//
//            System.out.printf("Category %d has %d pages (%d products)\n", categoryId, pages, numProducts);
//
//            for (; pageNo < pages; pageNo++) {
//                Page<ProductDTO> productsOnPage = productService.pageProducts(pageNo);
//                List<ProductDTO> productsOnPageForCategory = new ArrayList<>();
//                for (ProductDTO product : productsOnPage.getContent()) {
//                    if (product.getCategory().getId().equals(categoryId)) {
//                        productsOnPageForCategory.add(product);
//                    }
//                }
//                System.out.printf("Category %d, Page %d:\n", categoryId, pageNo + 1);
//                for (ProductDTO product : productsOnPageForCategory) {
//                    System.out.printf("  Product(id=%d)\n", product.getId());
//                }
//            }


//            int prodsInAPage = 12;
//            int pages = productService.getProductsInCategory(category.getId()).size() / prodsInAPage;
//
//            if (pages >= 1) {
//                pages = (productService.getProductsInCategory(category.getId()).size() / prodsInAPage) + 1;
//            } else {
//                pages = 1;
//            }
//
//            System.out.printf("Category %d have %d pages (%d products)\n",
//                    category.getId(), pages, productService.getProductsInCategory(category.getId()).size());
//
//            for (Product product : productService.getProductsInCategory(category.getId())) {
//                System.out.printf("Product(id=%d) in category %d\n", product.getId(), category.getId());
//            }
//            System.out.printf("Number of pages in category %d: %d\n", category.getId(), pages);
//
//            for (int i = 0; i < pages; i++) {
//                System.out.printf("Page no %d of category %d\n", i + 1, category.getId());
//                for (Product product : productService.getProductsInCategory(category.getId())) {
//                    for (ProductDTO productDTO : productService.pageProducts(i)) {
//                        if (productDTO.getId().equals(product.getId())) {
//                            System.out.printf("Page no %d of category %d have product with id=%s\n",
//                                    i + 1, category.getId(), productDTO.getId());
//                        }
//                    }
//                }
//            }


//            System.out.printf("Size of pageNoWithSpecificProductsForPerCategory after add category %d\n",
//                    category.getId());
//            System.out.println(pageNoWithSpecificProductsForPerCategory.size());

        }

        model.addAttribute("products", products);
        model.addAttribute("all_product", productService.findAll());
        model.addAttribute("brands", brandService.findAllByActivated());
        model.addAttribute("all_product_size", productService.getAll().size());

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
