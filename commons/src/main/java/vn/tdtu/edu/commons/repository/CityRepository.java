package vn.tdtu.edu.commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tdtu.edu.commons.model.City;
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
