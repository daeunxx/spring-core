package org.example.hellospring;

import javax.sql.DataSource;
import org.example.hellospring.data.JdbcOrderRepository;
import org.example.hellospring.order.OrderRepository;
import org.example.hellospring.order.OrderService;
import org.example.hellospring.order.OrderServiceImpl;
import org.example.hellospring.order.OrderServiceTxProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

  @Bean
  public OrderRepository orderRepository(DataSource dataSource) {
    return new JdbcOrderRepository(dataSource);
  }

  @Bean
  public OrderService orderService(OrderRepository orderRepository,
      PlatformTransactionManager transactionManager) {
    return new OrderServiceTxProxy(new OrderServiceImpl(orderRepository), transactionManager);
  }
}
