package vn.tdtu.edu.commons.service;

import vn.tdtu.edu.commons.dto.BrandDTO;
import vn.tdtu.edu.commons.dto.CategoryDTO;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.model.Category;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();
    Brand save(Brand brand);
    Brand findById(Long id);
    Brand update(Brand brand);
    void deleteById(Long id);
    void enableById(Long id);
    void disableById(Long id);
    List<Brand> findAllByActivated();
    /*Customer*/
    List<BrandDTO> getBrandAndProduct();



}
