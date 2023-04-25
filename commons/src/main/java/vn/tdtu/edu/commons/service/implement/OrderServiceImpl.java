package vn.tdtu.edu.commons.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.commons.dto.OrderDTO;
import vn.tdtu.edu.commons.model.Customer;
import vn.tdtu.edu.commons.model.Order;
import vn.tdtu.edu.commons.model.OrderDetail;
import vn.tdtu.edu.commons.repository.OrderDetailsRepository;
import vn.tdtu.edu.commons.repository.OrderRepository;
import vn.tdtu.edu.commons.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @Override
    public Order save(OrderDTO orderDTO) {
        Order order = new Order();
        order.setAddress(orderDTO.getAddress());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setCity(orderDTO.getCity());
        order.setCountry(orderDTO.getCountry());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setEmail(orderDTO.getEmail());
        order.setNotes(orderDTO.getNotes());
        order.setDeliveryDate(orderDTO.getDeliveryDate());
        order.setShippingFee(orderDTO.getShippingFee());
        order.setFull_name(orderDTO.getFull_name());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setCustomer(orderDTO.getCustomer());
        order.setPhoneNumber(orderDTO.getPhoneNumber());
        order.setOrderDetails(orderDTO.getOrderDetails());
        order = orderRepository.save(order);
        for(OrderDetail orderDetail: order.getOrderDetails()){
            orderDetail.setOrder(order);
            orderDetailsRepository.save(orderDetail);
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findByCustomerId(Long id) {
        return orderRepository.findByCustomerId(id);
    }
}
