package vn.tdtu.edu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.service.BrandService;

import java.security.Principal;
import java.util.List;

@Controller
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public String brands(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        List<Brand> brands = brandService.findAll();
        model.addAttribute("brands", brands);
        model.addAttribute("size", brands.size());
        model.addAttribute("title", "Brand");
        model.addAttribute("brandNew", new Brand());
        return "brands";
    }
}
