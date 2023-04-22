package vn.tdtu.edu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vn.tdtu.edu.commons.service.BrandService;
import vn.tdtu.edu.commons.service.CategoryService;
import vn.tdtu.edu.commons.service.ProductService;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
}
