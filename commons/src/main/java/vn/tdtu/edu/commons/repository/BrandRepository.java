package vn.tdtu.edu.commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.model.Category;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
