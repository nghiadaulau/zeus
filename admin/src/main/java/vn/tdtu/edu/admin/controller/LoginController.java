package vn.tdtu.edu.admin.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.tdtu.edu.commons.dto.AdminDTO;
import vn.tdtu.edu.commons.model.Admin;
import vn.tdtu.edu.commons.service.implement.AdminServiceImpl;

@Controller
public class LoginController {
    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }


    @RequestMapping(value = {"/", "/index"})
    public String home(Model model, AdminDTO adminDTO){
        model.addAttribute("title", "Admin Panel");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "redirect:/login";
        }
        model.addAttribute("username", authentication.getName());

        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("adminDTO", new AdminDTO());
        return "register";
    }

    @PostMapping("/register")
    public String addNewAdmin(@Valid @ModelAttribute("adminDTO") AdminDTO adminDTO,
                              BindingResult result,
                              Model model) {

        try {
            if (result.hasErrors()) {
                model.addAttribute("adminDTO", adminDTO);
                return "register";
            }
            String username = adminDTO.getUsername();
            Admin admin = adminService.findByUsername(username);
            if (admin != null) {
                model.addAttribute("adminDTO", adminDTO);
                model.addAttribute("emailError", "Your email has been registered!");
                return "register";
            }else if(adminService.findByEmail(adminDTO.getEmail()) != null){
                model.addAttribute("adminDTO", adminDTO);
                model.addAttribute("accountError", "Your account already exists!!");
                return "register";
            }
            if (adminDTO.getPassword().equals(adminDTO.getRepeatPassword())) {
                adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
                adminService.save(adminDTO);
                model.addAttribute("success", "Register successfully! Please login");
                model.addAttribute("adminDTO", adminDTO);
            } else {
                model.addAttribute("adminDTO", adminDTO);
                model.addAttribute("passwordError", "Your password maybe wrong! Check again!");
                System.out.println("password not same");
                return "register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errors", "The server has been wrong!");
        }
        return "register";

    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("title", "Forgot Password");
        return "forgot-password";
    }
}

