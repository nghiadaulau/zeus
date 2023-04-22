package vn.tdtu.edu.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandDTO {
    private Long brandId;
    private String brandName;
    private Long numberOfProduct;

}
