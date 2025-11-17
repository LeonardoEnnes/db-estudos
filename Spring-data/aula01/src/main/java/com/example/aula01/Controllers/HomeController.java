package com.example.aula01.Controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula01.Models.Customer;
import com.example.aula01.Services.CostomerService;

@RestController
@RequestMapping("/api/customers")
public class HomeController {
    private final CostomerService customerService;

    public HomeController(CostomerService customerService) {
        this.customerService = customerService;
    }

    // listando os customers
    @GetMapping("/listAllCustomers")
    public Iterable<Customer> getAllCustomers () {
        return customerService.findAllCustomers(); // Usa o método findAll() herdado do CrudRepository
    }

    @PostMapping("/addCostumer")
    public Customer addCustomer(@RequestBody Customer newCustomer) {
        // Customer newCustomer = new Customer(firstName, lastName);
        // return customerRepository.save(newCustomer); // Salva o novo cliente no banco de dados

        // O Spring já criou o Customer a partir do JSON do body
        // return customerRepository.save(newCustomer);
        return customerService.saveCostomer(newCustomer);
    }

    // retornando customer por id
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }
}
