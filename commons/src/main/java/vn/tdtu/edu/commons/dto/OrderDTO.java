package vn.tdtu.edu.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.tdtu.edu.commons.model.Customer;
import vn.tdtu.edu.commons.model.OrderDetail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    private Date orderDate;
    private Date deliveryDate;
    private double totalPrice;
    private double shippingFee;
    private int orderStatus;
    private String full_name;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String email;
    private String notes;
    private Customer customer;
    private List<OrderDetail> orderDetails;

    public OrderDTO transfer(CustomerDTO customerDTO){
        this.address = customerDTO.getAddress();
        this.city = customerDTO.getCity();
        this.country = customerDTO.getCountry();
        this.orderDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.orderDate);
        calendar.add(Calendar.DATE, 3);
        this.deliveryDate = calendar.getTime();
        this.full_name = customerDTO.getLastName() +" "+customerDTO.getFirstName();
        this.notes = customerDTO.getMessageOrder();
        this.email = customerDTO.getEmail();
        this.shippingFee = 0.0;
        this.orderStatus = 1;
        this.phoneNumber = customerDTO.getPhoneNumber();
        return this;
    }
}
