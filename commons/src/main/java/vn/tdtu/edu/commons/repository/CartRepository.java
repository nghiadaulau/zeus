package vn.tdtu.edu.commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tdtu.edu.commons.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
