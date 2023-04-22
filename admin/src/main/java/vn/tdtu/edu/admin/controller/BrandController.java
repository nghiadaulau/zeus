package vn.tdtu.edu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.model.Category;
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

    @PostMapping("/add-brand")
    public String add(@ModelAttribute("brandNew") Brand brand, RedirectAttributes attributes){
        try {
            brandService.save(brand);
            attributes.addFlashAttribute("success", "Added successfully");
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to add because duplicate name");
        }
        catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error server");
        }
        return "redirect:/brands";
    }
    @GetMapping(value = "brand/findById/{id}")
    @ResponseBody
    public Brand findById(@PathVariable("id")Long id){
        return brandService.findById(id);
    }

    @GetMapping("/update-brand")
    public String update(Brand brand, RedirectAttributes attributes){
        try{
            brandService.update(brand);
            attributes.addFlashAttribute("success","Updated successfully");
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error server");
        }
        return "redirect:/brands";
    }
    @GetMapping("/delete-brand/{id}")
    public String delete(@PathVariable("id")Long id, RedirectAttributes attributes){
        try {
            brandService.deleteById(id);
            attributes.addFlashAttribute("success", "Deleted successfully");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to deleted");
        }
        return "redirect:/brands";
    }

    @GetMapping("/enable-brand/{id}")
    public String enable(@PathVariable("id")Long id, RedirectAttributes attributes){
        try {
            brandService.enableById(id);
            attributes.addFlashAttribute("success", "Enabled successfully");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to enabled");
        }
        return "redirect:/brands";
    }

    @GetMapping( "/disable-brand/{id}")
    public String disable(@PathVariable("id") Long id, RedirectAttributes attributes){
        try {
            brandService.disableById(id);
            attributes.addFlashAttribute("success", "Disabled successfully");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to disabled");
        }
        return "redirect:/brands";
    }
}
