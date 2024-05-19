package org.example.email.controlers;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.example.email.dao.CustomerDAO;
import org.example.email.models.dto.CustomerDTO;
import org.example.email.models.views.Customer;
import org.example.email.services.CustomerService;
import org.example.email.models.views.Views;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerDAO customerDAO;
    private CustomerService customerService;

    @GetMapping("")
    @JsonView(Views.Client.class)
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> all = customerDAO.findAll();
        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
    }

    @GetMapping("/{id}")
    @JsonView({Views.Admin.class})
    public ResponseEntity<Customer> getOneCustomer(@PathVariable int id) {
        Customer customer = customerDAO.findById(id).get();
        return new ResponseEntity<>(customer,HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerDAO.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerDAO.findById(id).get();
        customer.setName(customerDTO.getUsername());
        customerDAO.save(customer);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable String name){
        return customerService.customerListByName(name);
    }

}
