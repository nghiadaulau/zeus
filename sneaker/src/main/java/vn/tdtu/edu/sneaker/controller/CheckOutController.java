package vn.tdtu.edu.sneaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.tdtu.edu.commons.dto.CustomerDTO;
import vn.tdtu.edu.commons.dto.OrderDTO;
import vn.tdtu.edu.commons.model.*;
import vn.tdtu.edu.commons.service.implement.CartServiceImpl;
import vn.tdtu.edu.commons.service.implement.CustomerServiceImpl;
import vn.tdtu.edu.commons.service.implement.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/check-out")
@Controller
public class CheckOutController {
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    CartServiceImpl cartService;

    @Autowired
    OrderServiceImpl orderService;
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
    @PostMapping("/order")
    public String Order(@ModelAttribute CustomerDTO customerDTO, RedirectAttributes ra){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findByUsername(authentication.getName());
        Cart cart = cartService.findByCustomerId(customer.getId());
        if(cart.getCartItem().size()!=0){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO = orderDTO.transfer(customerDTO);
            orderDTO.setCustomer(customer);
            orderDTO.setTotalPrice(cart.getTotalPrices());
            List<OrderDetail> orderDetailList = new ArrayList<>();
            List<Product> products = new ArrayList<>();
            for(CartItem cartItem: cart.getCartItem()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(cartItem.getProduct());
                orderDetail.setTotalPrice(cartItem.getTotalPrice());
                orderDetail.setQuantity(cartItem.getQuantity());
                orderDetail.setUnitPrice(cartItem.getProduct().getCostPrice());
                orderDetailList.add(orderDetail);
                products.add(cartItem.getProduct());
            }
            System.out.println(products);
            cartService.deleteItemsFromCart(products,customer);
            orderDTO.setOrderDetails(orderDetailList);
            orderService.save(orderDTO);
            ra.addFlashAttribute("message","Order successfully!");
        }
        else
        {
            ra.addFlashAttribute("message","Your cart is empty!");
        }
        return "redirect:/check-out/";
    }
}
