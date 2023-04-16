package vn.tdtu.edu.commons.service;

import vn.tdtu.edu.commons.dto.AdminDTO;
import vn.tdtu.edu.commons.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);

    Admin findByEmail(String email);

    Admin save(AdminDTO adminDto);
}