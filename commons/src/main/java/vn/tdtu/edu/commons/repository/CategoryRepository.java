package vn.tdtu.edu.commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.tdtu.edu.commons.model.Category;
import vn.tdtu.edu.commons.dto.CategoryDTO;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.is_activated = true and c.is_deleted = false")
    List<Category> findAllByActivated();


    /*Customer*/
    @Query("select new vn.tdtu.edu.commons.dto.CategoryDTO(c.id, c.name, count(p.category.id)) from Category c inner join Product p on p.category.id = c.id " +
            " where c.is_activated = true and c.is_deleted = false group by c.id")
    List<CategoryDTO> getCategoryAndProduct();

}
