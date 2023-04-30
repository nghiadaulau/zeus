package vn.tdtu.edu.commons.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.tdtu.edu.commons.dto.ProductDTO;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.model.Category;
import vn.tdtu.edu.commons.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /*Admin*/
    @Query("select p from Product p")
    Page<Product> pageProduct(Pageable pageable);

    @Query("select p from Product p where p.description like %?1% or p.name like %?1%")
    Page<Product> searchProducts(String keyword, Pageable pageable);

    @Query("select p from Product p where p.description like %?1% or p.name like %?1%")
    List<Product> searchProductsList(String keyword);

    /*Customer*/
    @Query("select p from Product p where p.is_activated = true and p.is_deleted = false")
    List<Product> getAllProducts();


    @Query(value = "select * from products p where p.is_deleted = false and p.is_activated = true order by rand() asc limit 4 ", nativeQuery = true)
    List<Product> listViewProducts();


    @Query(value = "select * from products p inner join categories c on c.category_id = p.category_id where p.category_id = ?1", nativeQuery = true)
    List<Product> getRelatedProducts(Long categoryId);


    @Query(value = "select p from Product p inner join Category c on c.id = p.category.id where c.id = ?1 and p.is_deleted = false and p.is_activated = true")
    List<Product> getProductsInCategory(Long categoryId);

    @Query(value = "select p from Product p inner join Brand b on b.id = p.brand.id where b.id = ?1 and b.is_deleted = false and b.is_activated = true")
    List<Product> getProductsInBrand(Long brandId);
    @Query(value = "select p from Product p inner join Brand b on b.id = p.brand.id join Category c  on c.id = p.category.id " +
            "where b.id = ?1 and b.is_deleted = false and b.is_activated = true " +
            "and c.id = ?1 and p.is_deleted = false and p.is_activated = true")
    List<Product> getProductsInBrandAndCategory(Long categoryId, Long brandId);
    @Query("select p from Product p where p.is_activated = true and p.is_deleted = false" +
            " order by p.costPrice desc")
    List<Product> filterHighPrice();

    @Query("select p from Product p where p.is_activated = true and p.is_deleted = false order by p.costPrice ")
    List<Product> filterLowPrice();

    @Query("SELECT p FROM Product p where p.is_activated = true and p.is_deleted = false ORDER BY function('RAND')")
    List<Product> findRandomProducts(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.brand.name like %:pBrand% or p.category.name like %:pCategory% ORDER BY function('RAND')")
    List<Product> findRandomProductsByBrandAndCategory(Pageable pageable, String pBrand, String pCategory);
    @Query(value = "select p from Product p inner join Category c on c.id = p.category.id where c.id = ?1 and p.is_deleted = false and p.is_activated = true")
    List<Product> findProductsByCategoryId(Long id, Pageable pageable);
    List<Product> findProductsByCategoryIdOrderByCostPriceDesc(Long id, Pageable pageable);
    List<Product> findProductsByCategoryIdOrderByCostPriceAsc(Long id, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Product p where p.brand.name like %?1% or p.name" +
            " like %?1% or p.category.name like %?1% or p.description like %?1%")
    List<Product> search(String s);
}
