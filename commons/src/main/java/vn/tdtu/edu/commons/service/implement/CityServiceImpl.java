package vn.tdtu.edu.commons.service.implement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.commons.repository.CityRepository;
import vn.tdtu.edu.commons.service.CityService;
import vn.tdtu.edu.commons.model.City;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }
}