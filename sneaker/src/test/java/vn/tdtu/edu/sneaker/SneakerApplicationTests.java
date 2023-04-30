package vn.tdtu.edu.sneaker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.model.Category;
import vn.tdtu.edu.commons.model.Product;
import vn.tdtu.edu.commons.service.implement.*;

import java.util.List;

@SpringBootTest
class SneakerApplicationTests {
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

        System.out.println("------------------Product service-------------------");
        System.out.println("find all products: " + productService.findAll());
        System.out.println("search products: " +
                productService.search("sneakers"));
        System.out.println("sort all product by DESC: " + productService.filterHighPrice());
        System.out.println("sort all product by ASC: " + productService.filterLowPrice());
    }

}
