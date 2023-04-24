package vn.tdtu.edu.commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tdtu.edu.commons.model.OrderDetail;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail,Long> {
}
