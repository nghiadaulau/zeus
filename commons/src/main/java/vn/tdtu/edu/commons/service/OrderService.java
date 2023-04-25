package vn.tdtu.edu.commons.service;

import vn.tdtu.edu.commons.dto.OrderDTO;
import vn.tdtu.edu.commons.model.Customer;
import vn.tdtu.edu.commons.model.Order;

import java.util.List;

public interface OrderService {
    Order save(OrderDTO orderDTO);

    List<Order> findByCustomerId(Long id);
}
