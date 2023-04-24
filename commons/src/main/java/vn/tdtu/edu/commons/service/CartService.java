package vn.tdtu.edu.commons.service;

import vn.tdtu.edu.commons.model.Cart;
import vn.tdtu.edu.commons.model.Customer;
import vn.tdtu.edu.commons.model.Product;

public interface CartService {
    Cart addItemToCart(Product product, int quantity, Customer customer);

    Cart updateItemInCart(Product product, int quantity, Customer customer);

    Cart deleteItemFromCart(Product product, Customer customer);
}
