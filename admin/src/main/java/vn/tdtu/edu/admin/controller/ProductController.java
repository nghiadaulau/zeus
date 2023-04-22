package vn.tdtu.edu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.tdtu.edu.commons.dto.ProductDTO;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.model.Category;
import vn.tdtu.edu.commons.model.Product;
import vn.tdtu.edu.commons.service.BrandService;
import vn.tdtu.edu.commons.service.CategoryService;
import vn.tdtu.edu.commons.service.ProductService;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    @GetMapping("/products")
    public String products(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        List<ProductDTO> productDTOList = productService.findAll();
        List<Category> categories = categoryService.findAllByActivated();
        List<Brand> brands = brandService.findAllByActivated();

        model.addAttribute("title", "Manage Product");
        model.addAttribute("products", productDTOList);
        model.addAttribute("size", productDTOList.size());
        model.addAttribute("username", principal.getName());
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        return "products";
    }

    @GetMapping("/products/{pageNo}")
    public String productsPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Page<ProductDTO> products = productService.pageProducts(pageNo);
        List<Category> categories = categoryService.findAllByActivated();
        List<Brand> brands = brandService.findAllByActivated();
        model.addAttribute("title", "Manage Product");
        model.addAttribute("size", products.getSize());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("products", products);
        model.addAttribute("username", principal.getName());
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        return "products";
    }

    @GetMapping("/search-result/{pageNo}")
    public String searchProducts(@PathVariable("pageNo")int pageNo,
                                 @RequestParam(value = "category", required = false) Long categoryId,
                                 @RequestParam(value = "brand", required = false) Long brandId,
                                 @RequestParam(value = "min_price", required = false) Double  minPrice,
                                 @RequestParam(value = "max_price", required = false) Double  maxPrice,
                                 @RequestParam(value = "product", required = false) String productName,
                                 Model model,
                                 Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Page<ProductDTO> products = productService.searchProducts(pageNo, categoryId, brandId, minPrice, maxPrice, productName);
        List<Category> categories = categoryService.findAllByActivated();
        List<Brand> brands = brandService.findAllByActivated();
        model.addAttribute("title", "Search Result");
        model.addAttribute("products", products);
        model.addAttribute("size", products.getSize());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("categories", categories);
        model.addAttribute("username", principal.getName());
        model.addAttribute("brands", brands);
        return "products";
    }
    @GetMapping("/add-product")
    public String addProductForm(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<Category> categories = categoryService.findAllByActivated();
        List<Brand> brands = brandService.findAllByActivated();
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("username", principal.getName());
        return "add-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product")ProductDTO productDTO,
                              @RequestParam("imageProduct") MultipartFile imageProduct,
                              RedirectAttributes attributes){
        try {
            productService.save(imageProduct, productDTO);
            attributes.addFlashAttribute("success", "Add successfully!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to add!");
        }
        return "redirect:/products/0";
    }

    @GetMapping("/update-product/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        model.addAttribute("title", "Update products");
        List<Category> categories = categoryService.findAllByActivated();
        List<Brand> brands = brandService.findAllByActivated();
        ProductDTO productDTO = productService.getById(id);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("username", principal.getName());
        return "update-product";
    }


    @PostMapping("/update-product/{id}")
    public String processUpdate(@PathVariable("id") Long id,
                                @ModelAttribute("productDto") ProductDTO productDTO,
                                @RequestParam("imageProduct")MultipartFile imageProduct,
                                RedirectAttributes attributes
    ){
        try {
            productService.update(imageProduct, productDTO);
            attributes.addFlashAttribute("success", "Update successfully!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to update!");
        }
        return "redirect:/products/0";

    }

    @GetMapping(value = "/enable-product/{id}")
    public String enabledProduct(@PathVariable("id")Long id, RedirectAttributes attributes){
        try {
            productService.enableById(id);
            attributes.addFlashAttribute("success", "Enabled successfully!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to enabled!");
        }
        return "redirect:/products/0";
    }

    @GetMapping(value = "/delete-product/{id}")
    public String deletedProduct(@PathVariable("id") Long id, RedirectAttributes attributes){
        try {
            productService.deleteById(id);
            attributes.addFlashAttribute("success", "Deleted successfully!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to deleted");
        }
        return "redirect:/products/0";
    }

    @GetMapping( "/disable-product/{id}")
    public String disable(@PathVariable("id") Long id, RedirectAttributes attributes){
        try {
            productService.disableById(id);
            attributes.addFlashAttribute("success", "Disabled successfully");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to disabled");
        }
        return "redirect:/products/0";
    }
}
