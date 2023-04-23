package vn.tdtu.edu.sneaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.tdtu.edu.commons.model.Cart;
import vn.tdtu.edu.commons.model.CartItem;
import vn.tdtu.edu.commons.model.Customer;
import vn.tdtu.edu.commons.model.Product;
import vn.tdtu.edu.commons.service.implement.CartServiceImpl;
import vn.tdtu.edu.commons.service.implement.CustomerServiceImpl;
import vn.tdtu.edu.commons.service.implement.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    ProductServiceImpl productService;
    @GetMapping("/")
    public String Index(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            Customer customer = customerService.findByUsername(authentication.getName());
            Cart cart = cartService.findByCustomerId(customer.getId());
            model.addAttribute("username",authentication.getName());
            if(cart==null){
                model.addAttribute("cartItems",new ArrayList<CartItem>());
            }
            else
            {
                model.addAttribute("cartItems",cart.getCartItem());
            }
            return "cart";
        }
        return "login";
    }
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long productID){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            Product product = productService.getProductById(productID);
            Customer customer = customerService.findByUsername(authentication.getName());
            cartService.addItemToCart(product,1,customer);
            return "redirect:/cart/";
        }
        return "login";
    }
    @GetMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable("id") Long productID){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            Product product = productService.getProductById(productID);
            Customer customer = customerService.findByUsername(authentication.getName());
            cartService.deleteItemFromCart(product,customer);
            return "redirect:/cart/";
        }
        return "login";
    }
    @PostMapping("/update")
    public String addToCart(@RequestBody Map<String, String> body){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            Product product = productService.getProductById(Long.valueOf(body.get("productId")));
            Customer customer = customerService.findByUsername(authentication.getName());
            cartService.updateItemInCart(product, Integer.parseInt(body.get("quantity")),customer);
            return "redirect:/cart/";
        }
        return "login";
    }
}
