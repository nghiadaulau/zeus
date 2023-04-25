package vn.tdtu.edu.commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tdtu.edu.commons.model.Customer;
import vn.tdtu.edu.commons.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomerId(Long id);
}
