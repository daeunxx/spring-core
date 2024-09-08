package org.example.hellospring.order;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Order createOrder(String orderNo, BigDecimal totalAmount) {
    Order order = new Order(orderNo, totalAmount);
    this.orderRepository.save(order);
    return order;
  }

  @Override
  public List<Order> createOrders(List<OrderRequest> requests) {
    return requests.stream()
        .map(request -> createOrder(request.orderNo(), request.totalAmount()))
        .toList();
  }
}
