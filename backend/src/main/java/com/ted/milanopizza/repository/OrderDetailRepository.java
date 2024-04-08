package com.ted.milanopizza.repository;

import com.ted.milanopizza.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

//    public interface OrderDetailWithAssociations {
//        Long getID();
//        Long getCustomerOrderID();
//        Long getProductID();
//        LocalDateTime getOrderDate();
//        Long getQuantity();
//        Long getDiscount();
//        Double getSubTotal();
//        String getProductName();
//    }
//
//    // write query to get the right stuff
//    @Query("SELECT od.ID as ID, od.customerOrder.ID as customerOrderID, od.product.ID as productID, od.product.name as productName, od.orderDate as orderDate, od.quantity as quantity, od.discount as discount, od.subTotal as subTotal FROM OrderDetail od WHERE od.customerOrder.ID = :customerOrderId")
//    List<OrderDetailWithAssociations> findOrderDetailByCustomerOrderIdWithAssociations(@Param("customerOrderId") Long Id);
//
//    @Query("SELECT od.ID as ID, od.customerOrder.ID as customerOrderID, od.product.ID as productID, od.product.name as productName, od.orderDate as orderDate, od.quantity as quantity, od.discount as discount, od.subTotal as subTotal FROM OrderDetail od WHERE od.product.ID = :productId")
//    List<OrderDetailWithAssociations> findOrderDetailByProductIdWithAssociations(@Param("productId") Long Id);
//
//    @Query("SELECT od.ID as ID, od.customerOrder.ID as customerOrderID, od.product.ID as productID, od.product.name as productName, od.orderDate as orderDate, od.quantity as quantity, od.discount as discount, od.subTotal as subTotal FROM OrderDetail od WHERE od.customerOrder.customer.zipcode.zipcodeID = :zipcodeId ORDER BY od.orderDate")
//    List<OrderDetailWithAssociations> findOrderDetailByZipcodeWithAssociations(@Param("zipcodeId") Long Id);
}
