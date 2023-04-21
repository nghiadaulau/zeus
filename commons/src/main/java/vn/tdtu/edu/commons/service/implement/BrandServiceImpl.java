package vn.tdtu.edu.commons.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.commons.model.Brand;
import vn.tdtu.edu.commons.repository.BrandRepository;
import vn.tdtu.edu.commons.service.BrandService;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository repository;
    @Override
    public Brand save(Brand brand) {
        return repository.save(brand);
    }
    public List<Brand> findAll() {
        return repository.findAll();
    }
}
