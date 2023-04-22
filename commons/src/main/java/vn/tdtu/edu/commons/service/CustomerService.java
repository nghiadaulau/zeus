package vn.tdtu.edu.commons.service;

import vn.tdtu.edu.commons.dto.CustomerDTO;
import vn.tdtu.edu.commons.model.Customer;

public interface CustomerService {

    CustomerDTO save(CustomerDTO customerDTO);

    Customer findByUsername(String username);

    Customer saveInfor(Customer customer);

    Customer findByEmail(String email);

    void update(CustomerDTO customerDTO);
}