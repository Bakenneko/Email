package org.example.email.services;

import lombok.AllArgsConstructor;
import org.example.email.dao.CustomerDAO;
import org.example.email.models.views.Customer;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class CustomerService {

    private CustomerDAO customerDAO;

    private MailService mailService;

    public void save(Customer customer) {

        customerDAO.save(customer);
        mailService.send(customer);
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
