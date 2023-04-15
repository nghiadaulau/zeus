package vn.tdtu.edu.commons.service;

import vn.tdtu.edu.commons.dto.CategoryDTO;
import vn.tdtu.edu.commons.model.Category;

import java.util.List;

public interface CategoryService {
    /*Admin*/
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void enabledById(Long id);
    List<Category> findAllByActivated();

    /*Customer*/
    List<CategoryDTO> getCategoryAndProduct();
}
