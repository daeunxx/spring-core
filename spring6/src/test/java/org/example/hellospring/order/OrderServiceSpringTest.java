package org.example.hellospring.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;
import org.example.hellospring.OrderConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceSpringTest {

  @Autowired
  OrderService orderService;

  @Autowired
  DataSource dataSource;

  @Test
  void createOrder() {
    Order order = orderService.createOrder("0100", BigDecimal.ONE);
    assertThat(order.getOrderId()).isGreaterThan(0);
  }

  @Test
  void createOrders() {
    List<OrderRequest> orderRequests = List.of(
        new OrderRequest("0300", BigDecimal.ONE),
        new OrderRequest("0300", BigDecimal.TWO)
    );

    assertThatThrownBy(() -> orderService.createOrders(orderRequests))
        .isInstanceOf(DataIntegrityViolationException.class);

    JdbcClient jdbcClient = JdbcClient.create(dataSource);
    Long count = jdbcClient.sql("select count(*) from orders where orderNo = '0300'")
        .query(Long.class).single();

    assertThat(count).isEqualTo(0);

    /* //성공 시나리오
    List<Order> orders = orderService.createOrders(orderRequests);
    Assertions.assertThat(orders).hasSize(2);
    orders.forEach(order -> Assertions.assertThat(order.getOrderId()).isGreaterThan(0));
    */
  }
}