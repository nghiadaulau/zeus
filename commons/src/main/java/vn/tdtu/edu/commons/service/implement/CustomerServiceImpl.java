package vn.tdtu.edu.commons.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.commons.service.CustomerService;
import vn.tdtu.edu.commons.repository.*;
import vn.tdtu.edu.commons.dto.*;
import vn.tdtu.edu.commons.model.*;

import java.util.Collections;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {

        Customer customer = new Customer();
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setEmail(customerDTO.getEmail());
        customer.setImage("avatar.jpg");
        customer.setRoles(Collections.singletonList(repository.findByName("CUSTOMER")));
        Customer customerSave = customerRepository.save(customer);
        return mapperDTO(customerSave);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer saveInfor(Customer customer) {
        Customer customer1 = customerRepository.findByUsername(customer.getUsername());
        customer1.setAddress(customer.getAddress());
        customer1.setCity(customer.getCity());
        customer1.setCountry(customer.getCountry());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        return customerRepository.save(customer1);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public void update(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByUsername(customerDTO.getUsername());
        customer.setAddress(customerDTO.getAddress());
        customer.setCity(customerDTO.getCity());
        customer.setCountry(customerDTO.getCountry());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customerRepository.save(customer);
    }
    public void update(String fileName, String username) {
        Customer customer = customerRepository.findByUsername(username);
        customer.setImage(fileName);
        customerRepository.save(customer);
    }

    private CustomerDTO mapperDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setUsername(customer.getUsername());
        return customerDTO;
    }
}