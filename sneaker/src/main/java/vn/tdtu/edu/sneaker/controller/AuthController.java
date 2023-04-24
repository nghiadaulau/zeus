package vn.tdtu.edu.sneaker.controller;

import jakarta.mail.Multipart;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.tdtu.edu.commons.dto.CustomerDTO;
import vn.tdtu.edu.commons.model.Customer;
import vn.tdtu.edu.commons.service.implement.CustomerServiceImpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ResourceLoader resourceLoader;
    @GetMapping("/account")
    public String information(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            model.addAttribute("username",authentication.getName());
            model.addAttribute("customerDTO",customerService.findByUsername(authentication.getName()));
        }
        return "information";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("customerDTO",new CustomerDTO());
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("customerDTO",new CustomerDTO());
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("customerDTO") CustomerDTO customerDTO,
                              BindingResult result,
                              Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDTO", customerDTO);
                return "register";
            }
            String username = customerDTO.getUsername();
            Customer customer = customerService.findByUsername(username);
            if (customer != null) {
                model.addAttribute("customerDTO", customerDTO);
                model.addAttribute("errors", "Your username has been registered!");
                return "register";
            }
            else if(customerService.findByEmail(customerDTO.getEmail()) != null){
                model.addAttribute("customerDTO", customerDTO);
                model.addAttribute("errors", "Your email already exists!!");
                return "register";
            }
            else if (customerDTO.getPassword().equals(customerDTO.getRepeatPassword())) {
                customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
                customerService.save(customerDTO);
                model.addAttribute("success", "Register successfully! Please login");
                model.addAttribute("customerDTO", customerDTO);
            }
            else {
                model.addAttribute("customerDTO", customerDTO);
                model.addAttribute("errors", "Your password maybe wrong! Check again!");
                return "register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errors", "The server has been wrong!");
        }
        return "register";
    }
    @PostMapping("/update")
    public String update(Model model, @ModelAttribute("customerDTO") CustomerDTO customerDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        customerDTO.setUsername(authentication.getName());
        customerService.update(customerDTO);
        return "redirect:/auth/account";
    }
    @PostMapping("/upload-avatar")
    public String uploadAvatar(@RequestParam("image") MultipartFile image) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        String fileName = formattedDateTime + image.getOriginalFilename();
        Resource resource = resourceLoader.getResource("classpath:/static/images/user/");
        File directory = resource.getFile();
        File file = new File(directory, fileName);
        image.transferTo(file);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        customerService.update(fileName,authentication.getName());
        return "redirect:/auth/account";
    }
}
