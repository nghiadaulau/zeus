package vn.tdtu.edu.commons;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
<<<<<<< Updated upstream
import vn.tdtu.edu.commons.model.Product;
import vn.tdtu.edu.commons.service.implement.*;
=======
import vn.tdtu.edu.commons.service.implement.BrandServiceImpl;
>>>>>>> Stashed changes

@SpringBootTest
class CommonsApplicationTests {
    @Autowired
    BrandServiceImpl brandServiceImpl;

    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    BrandServiceImpl brandService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    ProductServiceImpl productService;

    @Test
    void contextLoads() {
        System.out.println("------------------Admin service-------------------");
        String email = "nhatnghiatyper@gmail.com";
        System.out.println("find admin by email: " + email + "\n" + adminService.findByEmail(email));
        String username = "nghiahln";
        System.out.println("find admin by username: " + username + "\n" + adminService.findByUsername(username));

        System.out.println("------------------Brand service-------------------");
        System.out.println("find All brands: \n" + brandService.findAll());
        System.out.println("find brand by id: 1\n" + brandService.findById(1L));
        System.out.println("find All brands and product:\n" + brandService.getBrandAndProduct());
        System.out.println("find All by activated: \n" + brandService.findAllByActivated());

        System.out.println("------------------Category service-------------------");
        System.out.println("find All categories: \n" + categoryService.findAll());
        System.out.println("find category by id: 1\n" + categoryService.findById(1L));
        System.out.println("find All by activated: \n" + categoryService.findAllByActivated());

        System.out.println("------------------Customer service-------------------");
        System.out.println("find admin by email: " + email + "\n" + customerService.findByEmail(email));
        System.out.println("find admin by username: " + username + "\n" + customerService.findByUsername(username));

        System.out.println("------------------Order service-------------------");
        System.out.println("find orders by customer id: " + orderService.findByCustomerId(1L));

        System.out.println("------------------Product service-------------------");
        System.out.println("find all products: " + productService.findAll());
        System.out.println("find products by id: " + productService.getProductById(1L));
        System.out.println("find products by category id: " + productService.getProductsInCategory(1L));
        System.out.println("find products by brand id: " + productService.getProductsInBrand(1L));
        System.out.println("find products by specific conditions: " +
                productService.getProductsByConditions(0, 1L, null, null));
        System.out.println("search products: " +
                productService.search("sneakers"));
        System.out.println("sort all product by DESC: " + productService.filterHighPrice());
        System.out.println("sort all product by ASC: " + productService.filterLowPrice());
    }

}
