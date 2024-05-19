package org.example.email.services;

import org.example.email.dao.CustomerDAO;
import org.example.email.models.Customer;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO;

    private MailService mailService;

    public void save(Customer customer) {
        if (customer.getId() > 0) {
            customerDAO.save(customer);
        } else {
           throw new RuntimeException("id < 0, can't be empty");
        }
        mailService.send(customer.getEmail());
    }

    public ResponseEntity<List<Customer>> customerListByName(String name) {

        if (name == null && name.isBlank()) {
         List<Customer> customerByName = customerDAO.findCustomerByName(name);
            System.out.println(customerByName);
            return new ResponseEntity<>(customerByName, HttpStatusCode.valueOf(200));
        } else {
            throw new RuntimeException();
        }
    }
}
