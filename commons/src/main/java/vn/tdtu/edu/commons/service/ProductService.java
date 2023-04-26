package vn.tdtu.edu.commons.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;
import vn.tdtu.edu.commons.dto.ProductDTO;
import vn.tdtu.edu.commons.model.Category;
import vn.tdtu.edu.commons.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    /*Admin*/
    List<ProductDTO> findAll();

    Product save(MultipartFile imageProduct, ProductDTO productDto);

    Product update(MultipartFile imageProduct, ProductDTO productDto);

    void deleteById(Long id);

    void enableById(Long id);

    void disableById(Long id);

    ProductDTO getById(Long id);

    Page<ProductDTO> pageProducts(int pageNo);

    Page<ProductDTO> pageProductsCustom(int pageNo, String sortBy);

    Page<ProductDTO> searchProducts(int pageNo, Long categoryId, Long brandId, Double minPrice, Double maxPrice, String productName);

    /*Customer*/
    List<Product> getAllProducts();

    List<Product> listViewProducts();

    Product getProductById(Long id);

    List<Product> getRelatedProducts(Long categoryId);

    List<Product> getProductsInCategory(Long categoryId);

    List<Product> getProductsInBrand(Long brandId);

    List<Product> getProductsInBrandAndCategory(Long categoryId, Long brandId);

    List<Product> filterHighPrice();

    List<Product> filterLowPrice();

    Page<ProductDTO> searchProductsCus(int pageNo, String keyword);

    Page<ProductDTO> getProductsByConditions(int pages, Long category_id, Long brand_id, String sortBy);

//    Page<ProductDTO> getProductsByConditionsCustom(int pages, Long category_id,
//                                                   Long brand_id, String sortBy, String s);

    List<Product> findRandomProducts();

    List<Product> findRandomProductsByBrandAndCategory(Product product);

    Map<Long, List<Product>> get4ProductsByCategoryId(List<Category> categories);

    List<Product> getProductsForPerCategoryByCategory(Category category, int pageNo, int pageSize);

    List<Product> getProductsForPerCategoryByCategoryID(Long categoryID, int pageNo, int pageSize);

    List<Product> getProductsForPerCategoryByCategoryIdOrderByCostPriceDesc(Category category, int pageNo, int pageSize);

    List<Product> getProductsForPerCategoryByCategoryIdOrderByCostPriceAsc(Category category, int pageNo, int pageSize);

    List<Product> search(String s, String sortBy);

}
