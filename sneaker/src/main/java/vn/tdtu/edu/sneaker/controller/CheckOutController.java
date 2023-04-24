package vn.tdtu.edu.sneaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.tdtu.edu.commons.dto.CustomerDTO;
import vn.tdtu.edu.commons.model.Cart;
import vn.tdtu.edu.commons.model.CartItem;
import vn.tdtu.edu.commons.model.Customer;
import vn.tdtu.edu.commons.service.implement.CartServiceImpl;
import vn.tdtu.edu.commons.service.implement.CustomerServiceImpl;

import java.util.ArrayList;

@RequestMapping("/check-out")
@Controller
public class CheckOutController {
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    CartServiceImpl cartService;
    @GetMapping("/")
    public String Index(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getAuthorities().toString().equals("[ROLE_ANONYMOUS]")){
            Customer customer = customerService.findByUsername(authentication.getName());
            Cart cart = cartService.findByCustomerId(customer.getId());
            model.addAttribute("username",authentication.getName());
            model.addAttribute("customerDTO",new CustomerDTO().transfer(customer));
            if(cart==null){
                model.addAttribute("cartItems",new ArrayList<CartItem>());
                model.addAttribute("cart",new Cart());
            }
            else
            {
                model.addAttribute("cartItems",cart.getCartItem());
                model.addAttribute("cart",cart);
            }
            return "checkout";
        }
        return "redirect:/auth/login";
    }
}
