package vn.tdtu.edu.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Size;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.model.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double costPrice;
    private double salePrice;
    private int currentQuantity;
    private Category category;
    private Brand brand;
    private String image;
    private boolean activated;
    private boolean deleted;
}