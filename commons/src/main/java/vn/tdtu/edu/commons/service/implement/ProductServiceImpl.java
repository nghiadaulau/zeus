package vn.tdtu.edu.commons.service.implement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.tdtu.edu.commons.service.ProductService;
import vn.tdtu.edu.commons.repository.*;
import vn.tdtu.edu.commons.dto.*;
import vn.tdtu.edu.commons.model.*;
import vn.tdtu.edu.commons.utils.ImageUpload;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageUpload imageUpload;

    /*Admin*/
    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return transfer(products).subList(0, 8);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(MultipartFile imageProduct, ProductDTO productDTO) {
        try {
            Product product = new Product();
            if (imageProduct == null) {
                product.setImage(null);
            } else {
                imageUpload.uploadImage(imageProduct, imageProduct.getOriginalFilename());
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            System.out.println(product.getImage());
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setCategory(productDTO.getCategory());
            product.setCostPrice(productDTO.getCostPrice());
            product.setSalePrice(productDTO.getSalePrice());
            product.setCurrentQuantity(productDTO.getCurrentQuantity());
            product.setBrand(productDTO.getBrand());
            product.set_activated(true);
            product.set_deleted(false);
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(MultipartFile imageProduct, ProductDTO productDTO) {
        try {
            Product product = productRepository.getById(productDTO.getId());
            if (imageProduct == null || imageProduct.isEmpty()) {
                product.setImage(product.getImage());
            } else {
                imageUpload.uploadImage(imageProduct, imageProduct.getOriginalFilename());
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setSalePrice(productDTO.getSalePrice());
            product.setCostPrice(productDTO.getCostPrice());
            product.setCurrentQuantity(productDTO.getCurrentQuantity());
            product.setCategory(productDTO.getCategory());
            product.setBrand(productDTO.getBrand());
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        Product product = productRepository.getReferenceById(id);
        product.set_deleted(true);
        product.set_activated(false);
        productRepository.save(product);
    }

    @Override
    public void enableById(Long id) {
        Product product = productRepository.getById(id);
        product.set_activated(true);
        product.set_deleted(false);
        productRepository.save(product);
    }

    @Override
    public void disableById(Long id) {
        Product product = productRepository.getById(id);
        product.set_activated(false);
        product.set_deleted(false);
        productRepository.save(product);

    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = productRepository.getById(id);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setCurrentQuantity(product.getCurrentQuantity());
        productDTO.setCategory(product.getCategory());
        productDTO.setSalePrice(product.getSalePrice());
        productDTO.setCostPrice(product.getCostPrice());
        productDTO.setImage(product.getImage());
        productDTO.setBrand(product.getBrand());
        productDTO.setDeleted(product.is_deleted());
        productDTO.setActivated(product.is_activated());
        return productDTO;
    }

    @Override
    public Page<ProductDTO> pageProducts(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 12);
        List<ProductDTO> products = transfer(productRepository.findAll());
        Page<ProductDTO> productPages = toPage(products, pageable);

        return productPages;
    }


    @Override
    public Page<ProductDTO> pageProductsCustom(int pageNo, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, 12);
        List<ProductDTO> products = new ArrayList<>();

        if (sortBy.equals("filterByDesc")) {
            products = transfer(productRepository.filterHighPrice());
        } else if (sortBy.equals("filterByAsc")) {
            products = transfer(productRepository.filterLowPrice());
        } else {
            products = transfer(productRepository.findAll());
        }

        Page<ProductDTO> productPages = toPage(products, pageable);

        return productPages;
    }

    @Override
    public Page<ProductDTO> searchProductsCus(int pageNo, String keyword, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, 12);
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> found = productRepository.search(keyword);

        if (sortBy != null) {
            if (sortBy.equals("filterByAsc")) {
                found.sort(Comparator.comparingDouble(Product::getCostPrice));
                productDTOList = transfer(found);
            }
            if (sortBy.equals("filterByDesc")) {
                found.sort(Comparator.comparingDouble(Product::getCostPrice).reversed());
                productDTOList = transfer(found);
            }
        } else {
            productDTOList = transfer(found);
        }

        Page<ProductDTO> products = toPage(productDTOList, pageable);

        return products;
    }

    @Override
    public Page<ProductDTO> getProductsByConditions(int pages, Long category_id, Long brand_id, String sortBy) {
        Pageable pageable = PageRequest.of(pages, 12);
        List<Product> filteredProducts = new ArrayList<>();

        if (brand_id != null && category_id != null) {
            if (brand_id == 0 && category_id == 0) {
                filteredProducts.addAll(productRepository.getAllProducts());
            }
        }

        for (Product product : productRepository.getAllProducts()) {
            if (category_id != null && !product.getCategory().getId().equals(category_id)) {
                continue;
            }

            if (brand_id != null && !product.getBrand().getId().equals(brand_id)) {
                continue;
            }

            filteredProducts.add(product);
        }

        if (sortBy != null) {
            if (sortBy.equals("filterByAsc")) {
                filteredProducts.sort(Comparator.comparingDouble(Product::getCostPrice));
            }
            if (sortBy.equals("filterByDesc")) {
                filteredProducts.sort(Comparator.comparingDouble(Product::getCostPrice).reversed());
            }
        }

        List<ProductDTO> productDTOList = transfer(filteredProducts);
        Page<ProductDTO> products = toPage(productDTOList, pageable);

        return products;
    }

    @Override
    public List<Product> findRandomProducts() {
        return productRepository.findRandomProducts(PageRequest.of(0, 6));
    }

    @Override
    public List<Product> findRandomProductsByBrandAndCategory(Product product) {
        return productRepository.findRandomProductsByBrandAndCategory(PageRequest.of(0, 6),
                product.getBrand().getName(),
                product.getCategory().getName());
    }

    @Override
    public Page<ProductDTO> searchProducts(int pageNo, Long categoryId, Long brandId, Double minPrice, Double maxPrice, String productName) {
        Pageable pageable = PageRequest.of(pageNo, 10);
        List<Product> filteredProducts = new ArrayList<>();
        List<Product> allProducts = productRepository.findAll();

        for (Product product : allProducts) {
            if (categoryId != null && !product.getCategory().getId().equals(categoryId)) {
                continue;
            }

            if (brandId != null && !product.getBrand().getId().equals(brandId)) {
                continue;
            }

            if (minPrice != null && Double.compare(product.getCostPrice(), minPrice) < 0) {
                continue;
            }

            if (maxPrice != null && Double.compare(product.getCostPrice(), maxPrice) > 0) {
                continue;
            }

            if (productName != null && !product.getName().toLowerCase().contains(productName.toLowerCase())) {
                continue;
            }

            filteredProducts.add(product);
        }

        List<ProductDTO> productDTOList = transfer(filteredProducts);
        Page<ProductDTO> products = toPage(productDTOList, pageable);
        return products;
    }

    private Page toPage(List<ProductDTO> list, Pageable pageable) {
        if (pageable.getOffset() >= list.size()) {
            return Page.empty();
        }

        int startIndex = (int) pageable.getOffset();

        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
                ? list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());

        List<ProductDTO> subList = list.subList(startIndex, endIndex);

        return new PageImpl(subList, pageable, list.size());
    }


    private List<ProductDTO> transfer(List<Product> products) {
        List<ProductDTO> productDtoList = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setCurrentQuantity(product.getCurrentQuantity());
            productDTO.setCategory(product.getCategory());
            productDTO.setSalePrice(product.getSalePrice());
            productDTO.setCostPrice(product.getCostPrice());
            productDTO.setImage(product.getImage());
            productDTO.setDeleted(product.is_deleted());
            productDTO.setActivated(product.is_activated());
            productDTO.setBrand(product.getBrand());
            productDtoList.add(productDTO);
        }

        return productDtoList;
    }

    /*Customer*/

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public List<Product> listViewProducts() {
        return productRepository.listViewProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public List<Product> getRelatedProducts(Long categoryId) {
        return productRepository.getRelatedProducts(categoryId);
    }

    @Override
    public List<Product> getProductsInCategory(Long categoryId) {
        return productRepository.getProductsInCategory(categoryId);
    }

    @Override
    public List<Product> getProductsInBrand(Long brandId) {
        return productRepository.getProductsInBrand(brandId);
    }

    @Override
    public List<Product> getProductsInBrandAndCategory(Long categoryId, Long brandId) {
        return productRepository.getProductsInBrandAndCategory(categoryId, brandId);
    }

    @Override
    public List<Product> filterHighPrice() {
        return productRepository.filterHighPrice();
    }

    @Override
    public List<Product> filterLowPrice() {
        return productRepository.filterLowPrice();
    }

    @Override
    public Map<Long, List<Product>> get4ProductsByCategoryId(List<Category> categories) {
        Map<Long, List<Product>> get4Products = new HashMap<>();
        Pageable topFour = PageRequest.of(0, 4);

        for (Category i : categories) {
            get4Products.put(i.getId(), productRepository.findProductsByCategoryId(i.getId(), topFour));
        }

        return get4Products;
    }

    @Override
    public List<Product> getProductsForPerCategoryByCategory(Category category, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findProductsByCategoryId(category.getId(), pageable);
    }

    @Override
    public List<Product> getProductsForPerCategoryByCategoryID(Long categoryID, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findProductsByCategoryId(categoryID, pageable);
    }

    @Override
    public List<Product> getProductsForPerCategoryByCategoryIdOrderByCostPriceDesc(Category category, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findProductsByCategoryIdOrderByCostPriceDesc(category.getId(), pageable);
    }

    @Override
    public List<Product> getProductsForPerCategoryByCategoryIdOrderByCostPriceAsc(Category category, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findProductsByCategoryIdOrderByCostPriceAsc(category.getId(), pageable);
    }

    public List<Product> search(String s) {
        return productRepository.search(s);
    }
}