package com.ted.milanopizza.controller;

import com.ted.milanopizza.dto.OrderDetailRequest;
import com.ted.milanopizza.model.CustomerOrder;
import com.ted.milanopizza.model.OrderDetail;
import com.ted.milanopizza.model.Product;
import com.ted.milanopizza.repository.CustomerOrderRepository;
import com.ted.milanopizza.repository.OrderDetailRepository;
import com.ted.milanopizza.repository.ProductRepository;
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
public class OrderDetailController {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("customerOrder/{id}/orderDetail")
    public ResponseEntity<List<OrderDetailRepository.OrderDetailWithAssociations>> getOrderDetailByCustomerOrderId(@PathVariable Long id) {
        List<OrderDetailRepository.OrderDetailWithAssociations> orderDetailData = orderDetailRepository.findOrderDetailByCustomerOrderIdWithAssociations(id);
        if (orderDetailData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetailData, HttpStatus.OK);
    }

    @GetMapping("product/{id}/orderDetail")
    public ResponseEntity<List<OrderDetailRepository.OrderDetailWithAssociations>> getOrderDetailByProductId(@PathVariable Long id) {
        List<OrderDetailRepository.OrderDetailWithAssociations> orderDetailData = orderDetailRepository.findOrderDetailByProductIdWithAssociations(id);
        if (orderDetailData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetailData, HttpStatus.OK);
    }

    @GetMapping("zipcode/{id}/orderDetail")
    public ResponseEntity<List<OrderDetailRepository.OrderDetailWithAssociations>> getOrderDetailByZipcode(@PathVariable Long id) {
        List<OrderDetailRepository.OrderDetailWithAssociations> orderDetailData = orderDetailRepository.findOrderDetailByZipcodeWithAssociations(id);
        if (orderDetailData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetailData, HttpStatus.OK);
    }

    @PostMapping("customerOrder/{id}/orderDetail")
    public ResponseEntity<OrderDetail> addOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
        // find Customer Order and Product associated with this detail
        Optional<CustomerOrder> customerOrderOptional = customerOrderRepository.findById(orderDetailRequest.getCustomerOrderId());
        Optional<Product> productOptional = productRepository.findById(orderDetailRequest.getProductId());
        if (customerOrderOptional.isEmpty() || productOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // retreive those objects and populate new object with it
        CustomerOrder existingCustomerOrder = customerOrderOptional.get();
        Product existingProduct = productOptional.get();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCustomerOrder(existingCustomerOrder);
        orderDetail.setProduct(existingProduct);
        orderDetail.setOrderDateTime(orderDetailRequest.getOrderDateTime());
        orderDetail.setQuantity(orderDetailRequest.getQuantity());
        orderDetail.setDiscount(orderDetailRequest.getDiscount());
        orderDetail.setSubTotal(orderDetailRequest.getSubTotal());

        orderDetailRepository.save(orderDetail);
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);

    }
}
