package vn.tdtu.edu.sneaker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.model.Category;
import vn.tdtu.edu.commons.model.Product;
import vn.tdtu.edu.commons.service.implement.BrandServiceImpl;
import vn.tdtu.edu.commons.service.implement.CategoryServiceImpl;
import vn.tdtu.edu.commons.service.implement.ProductServiceImpl;

import java.util.List;

@SpringBootTest
class SneakerApplicationTests {
    @Autowired
    public ProductServiceImpl productService;

    @Autowired
    public CategoryServiceImpl categoryService;

    @Autowired
    public BrandServiceImpl brandService;
    @Test
    void contextLoads() {
        Category category = new Category();
        Brand brand = new Brand();
        String [] arrCate={"King lord","Lord King","cate 1","cate 3","cate 4"};
        String [] arrBrand={"brand 1","brand 2","brand 3","brand 4","brand 5"};

  /*      for(long i = 0L;i<arrCate.length;i++){
            category.setId(i+1);
            category.setName(arrCate[(int) i]);
            categoryService.save(category);
            brand.setId(i+1);
            brand.setName(arrBrand[(int) i]);
            brandService.save(brand);
        }*/
        List<Category> categories = categoryService.findAll();
        List<Brand> brands= brandService.findAll();
        for(int i = 0 ; i<9;i++){
            Product product = new Product();
            product.setName("product "+ i);
            product.setId((long) (i + 1));
            product.setCategory(categories.get(i%4));
            product.setBrand(brands.get(i%4));
            product.setDescription("Nhu cai dau buoi "+ i);
            product.setImage("product.jpg");
            product.setCostPrice(10000 + i);
            product.setSalePrice(15000 + i);
            product.setCurrentQuantity(30 + i);
            productService.save(product);
        }
        System.out.println(productService.findAll());
    }

}
