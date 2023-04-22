package vn.tdtu.edu.commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.tdtu.edu.commons.dto.BrandDTO;
import vn.tdtu.edu.commons.dto.CategoryDTO;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.model.Category;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query("select b from Brand b where b.is_activated = true and b.is_deleted = false")
    List<Brand> findAllByActivated();


    /*Customer*/
    @Query("select new vn.tdtu.edu.commons.dto.BrandDTO(b.id, b.name, count(p.brand.id)) from Brand b inner join Product p on p.brand.id = b.id " +
            " where b.is_activated = true and b.is_deleted = false group by b.id")
    List<BrandDTO> getBrandAndProduct();
}
