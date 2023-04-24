package vn.tdtu.edu.commons.service;

import vn.tdtu.edu.commons.dto.OrderDTO;
import vn.tdtu.edu.commons.model.Order;

public interface OrderService {
    Order save(OrderDTO orderDTO);
}
