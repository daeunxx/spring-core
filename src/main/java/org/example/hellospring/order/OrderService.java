package org.example.hellospring.order;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final PlatformTransactionManager transactionManager;

  public OrderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
    this.orderRepository = orderRepository;
    this.transactionManager = transactionManager;
  }

  public Order createOrder(String orderNo, BigDecimal totalAmount) {
    Order order = new Order(orderNo, totalAmount);

    return new TransactionTemplate(transactionManager).execute(status -> {
      this.orderRepository.save(order);
      return order;
    });
  }
}
