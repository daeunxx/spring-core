package org.example.hellospring.order;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.example.hellospring.OrderConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceSpringTest {

  @Autowired
  OrderService orderService;

  @Test
  void createOrder() {
    Order order = orderService.createOrder("0100", BigDecimal.ONE);
    Assertions.assertThat(order.getOrderId()).isGreaterThan(0);
  }
}