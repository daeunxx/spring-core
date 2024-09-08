package org.example.hellospring.order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

  Order createOrder(String orderNo, BigDecimal totalAmount);

  List<Order> createOrders(List<OrderRequest> requests);
}
