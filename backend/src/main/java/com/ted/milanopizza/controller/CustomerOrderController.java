package com.ted.milanopizza.controller;

import com.ted.milanopizza.dto.CustomerOrderRequest;
import com.ted.milanopizza.model.Customer;
import com.ted.milanopizza.model.CustomerOrder;
import com.ted.milanopizza.model.Employee;
import com.ted.milanopizza.repository.CustomerOrderRepository;
import com.ted.milanopizza.repository.CustomerRepository;
import com.ted.milanopizza.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:5173")

@RestController
public class CustomerOrderController {
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;



    @GetMapping("/customerOrder")
    public ResponseEntity<List<CustomerOrderRepository.CustomerOrderWithAssociations>> getAllCustomerOrdersWithAssociations() {
        try {
            List<CustomerOrderRepository.CustomerOrderWithAssociations> customerOrderList = customerOrderRepository.findAllWithAssociations();

            if (customerOrderList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customerOrderList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customerOrder/{id}")
    public ResponseEntity<List<CustomerOrderRepository.CustomerOrderWithAssociations>> getCustomerOrderById(@PathVariable Long id) {
        List<CustomerOrderRepository.CustomerOrderWithAssociations> customerOrderData = customerOrderRepository.findByCustomerOrderIdWithAssociations(id);

        if (!customerOrderData.isEmpty()) {

            return new ResponseEntity<>(customerOrderData, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // this should ideally grab orders either by employee or customer
    @GetMapping("customer/{id}/customerOrder")
    public ResponseEntity<List<CustomerOrderRepository.CustomerOrderWithAssociations>> getCustomerOrderByCustomerId(@PathVariable Long id) {
        List<CustomerOrderRepository.CustomerOrderWithAssociations> customerOrderData = customerOrderRepository.findByIdWithAssociations(id);
        if (customerOrderData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerOrderData, HttpStatus.OK);
    }

    @GetMapping("zipcode/{id}/customerOrder")
    public ResponseEntity<List<CustomerOrderRepository.CustomerOrderWithAssociations>> getCustomerOrderByZipcode(@PathVariable Long id) {
        List<CustomerOrderRepository.CustomerOrderWithAssociations> customerOrderData = customerOrderRepository.findByZipcodeWithAssociations(id);
        if (customerOrderData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerOrderData, HttpStatus.OK);
    }

    @GetMapping("employee/{id}/customerOrder")
    public ResponseEntity<List<CustomerOrderRepository.CustomerOrderWithAssociations>> getCustomerOrderByEmployeeID(@PathVariable Long id) {
        List<CustomerOrderRepository.CustomerOrderWithAssociations> customerOrderData = customerOrderRepository.findByEmployeeIDWithAssociations(id);
        if (customerOrderData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerOrderData, HttpStatus.OK);
    }


    // I prefer a DTO to transfer the ID's in a clean way
    // this needs to get added to both customer and employee customerOrder lists!
    @PostMapping("/customerOrder")
    public ResponseEntity<CustomerOrder> addCustomerOrder(@RequestBody CustomerOrderRequest customerOrderRequest) {
        // find customer and employee and associate with this order
        Optional<Customer> customerOptional = customerRepository.findById(customerOrderRequest.getTelephone());
        Optional<Employee> employeeOptional = employeeRepository.findById(customerOrderRequest.getEmployeeId());
        if (customerOptional.isEmpty() || employeeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // retrieve customer and employee from db
        Customer existingCustomer = customerOptional.get();
        Employee existingEmployee = employeeOptional.get();

        // instantiate customerOrder and set the values
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(existingCustomer);
        customerOrder.setEmployee(existingEmployee);

        // save to db and return OK response
        customerOrderRepository.save(customerOrder);
        return new ResponseEntity<>(customerOrder, HttpStatus.OK);

    }

    @PostMapping("/customerOrder/{id}")
    public ResponseEntity<CustomerOrder> updateCustomerOrderById(@PathVariable int id, @RequestBody CustomerOrder newCustomerOrder)
    {
        Optional<CustomerOrder> oldCustomerOrder = customerOrderRepository.findById(id);

        if(oldCustomerOrder.isPresent())
        {
            CustomerOrder updatedCustomerOrder = oldCustomerOrder.get();
            if(newCustomerOrder.getCustomerOrderDateTime() != null)
            {
                updatedCustomerOrder.setCustomerOrderDateTime(newCustomerOrder.getCustomerOrderDateTime());
            }
            //
            CustomerOrder customerOrderObj = customerOrderRepository.save(updatedCustomerOrder);
            return new ResponseEntity<>(customerOrderObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
