package vn.tdtu.edu.commons.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import vn.tdtu.edu.commons.dto.ProductDTO;
import vn.tdtu.edu.commons.model.Product;

import java.util.List;

public interface ProductService {
    /*Admin*/
    List<ProductDTO> findAll();
    Product save(MultipartFile imageProduct, ProductDTO productDto);
    Product update(MultipartFile imageProduct, ProductDTO productDto);
    void deleteById(Long id);
    void enableById(Long id);
    ProductDTO getById(Long id);

    Page<ProductDTO> pageProducts(int pageNo);

    Page<ProductDTO> searchProducts(int pageNo, String keyword);


    /*Customer*/
    List<Product> getAllProducts();

    List<Product> listViewProducts();

    Product getProductById(Long id);

    List<Product> getRelatedProducts(Long categoryId);

    List<Product> getProductsInCategory(Long categoryId);

    List<Product> filterHighPrice();

    List<Product> filterLowPrice();
}
