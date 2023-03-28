package com.shreek.bookmanagement.controller;

import com.shreek.bookmanagement.dto.ResponseDTO;
import com.shreek.bookmanagement.model.Customer;
import com.shreek.bookmanagement.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerRepo customerRepo;
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer newcustomer = new Customer();
        newcustomer.setName(customer.getName());
        newcustomer.setEmail(customer.getEmail());
        newcustomer.setPassword(customer.getPassword());

        Customer added = customerRepo.save(newcustomer);

        return new  ResponseEntity<>(added, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable("id") int id){
        return Optional.ofNullable(customerRepo.findById(id).orElseThrow(() ->
                new Error("Customer not found by id" + id)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomerById(@RequestBody Customer customer, @PathVariable("id") int id){
        Customer oldcustomer = customerRepo.findById(id).orElseThrow(()->
                new Error("Customer Not found by id"+id));
        oldcustomer.setName(customer.getName());
        oldcustomer.setEmail(customer.getEmail());
        oldcustomer.setPassword(customer.getPassword());

        Customer updated = customerRepo.save(oldcustomer);
        return new ResponseEntity<Customer>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteCustomerById(@PathVariable("id") int id){
        Optional<Customer> customer = customerRepo.findById(id);
        if(customer.isPresent()) customerRepo.deleteById(id);
        else throw new Error("Customer not found by id"+id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Customer successfully deleted");

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}