package vn.tdtu.edu.commons.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.commons.dto.AdminDTO;
import vn.tdtu.edu.commons.model.Admin;
import vn.tdtu.edu.commons.service.AdminService;
import vn.tdtu.edu.commons.repository.AdminRepository;
import vn.tdtu.edu.commons.repository.RoleRepository;

import java.util.Arrays;
import java.util.Collections;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDTO adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRoles(Collections.singletonList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(admin);
    }
}