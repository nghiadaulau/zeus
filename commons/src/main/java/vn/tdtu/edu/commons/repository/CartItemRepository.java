package vn.tdtu.edu.commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tdtu.edu.commons.model.Admin;
import vn.tdtu.edu.commons.model.CartItem;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
