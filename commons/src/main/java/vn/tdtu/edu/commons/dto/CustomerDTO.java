package vn.tdtu.edu.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;
import vn.tdtu.edu.commons.model.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @Size(min = 3, max = 15, message = "First name should have 3-15 characters")
    private String firstName;

    @Size(min = 3, max = 15, message = "Last name should have 3-15 characters")
    private String lastName;

    private String username;

    private String email;
    private String phoneNumber;
    private String address;
    private String country;
    private String city;
    @Size(min = 5, max = 20, message = "Password should have 5-20 characters")
    private String password;

    private String repeatPassword;

    private String messageOrder;

    public CustomerDTO transfer(Customer customer){
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.username = customer.getUsername();
        this.city = customer.getCity();
        this.country = customer.getCountry();
        this.address = customer.getAddress();
        this.phoneNumber = customer.getPhoneNumber();
        this.email = customer.getEmail();
        return this;
    }
}
