package vn.tdtu.edu.commons.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.tdtu.edu.commons.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);

    Admin findAdminByEmail(String email);
}