package vn.tdtu.edu.commons.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.commons.dto.BrandDTO;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.model.Category;
import vn.tdtu.edu.commons.repository.BrandRepository;
import vn.tdtu.edu.commons.service.BrandService;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand save(Brand brand) {
        Brand brandSave = new Brand(brand.getName());
        return brandRepository.save(brandSave);
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).get();
    }

    @Override
    public Brand update(Brand brand) {
        Brand brandUpdate = null;
        try {
            brandUpdate = brandRepository.findById(brand.getId()).get();
            brandUpdate.setName(brand.getName());
            brandUpdate.set_activated(brand.is_activated());
            brandUpdate.set_deleted(brand.is_deleted());
        }catch (Exception e){
            e.printStackTrace();
        }
        return brandRepository.save(brandUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Brand brand = brandRepository.getById(id);
        brand.set_deleted(true);
        brand.set_activated(false);
        brandRepository.save(brand);

    }

    @Override
    public void enableById(Long id) {
        Brand brand = brandRepository.getById(id);
        brand.set_deleted(false);
        brand.set_activated(true);
        brandRepository.save(brand);

    }

    @Override
    public void disableById(Long id) {
        Brand brand = brandRepository.getById(id);
        brand.set_deleted(false);
        brand.set_activated(false);
        brandRepository.save(brand);

    }

    @Override
    public List<Brand> findAllByActivated() {
        return brandRepository.findAllByActivated();
    }

    @Override
    public List<BrandDTO> getBrandAndProduct() {
        return brandRepository.getBrandAndProduct();
    }
}
