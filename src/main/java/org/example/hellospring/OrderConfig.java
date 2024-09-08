package org.example.hellospring;

import javax.sql.DataSource;
import org.example.hellospring.data.JdbcOrderRepository;
import org.example.hellospring.order.OrderRepository;
import org.example.hellospring.order.OrderService;
import org.example.hellospring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(DataConfig.class)
@EnableTransactionManagement
public class OrderConfig {

  @Bean
  public OrderRepository orderRepository(DataSource dataSource) {
    return new JdbcOrderRepository(dataSource);
  }

  @Bean
  public OrderService orderService(OrderRepository orderRepository) {
    return new OrderServiceImpl(orderRepository);
  }

  /*
  // 프록시 사용
  @Bean
  public OrderService orderService(OrderRepository orderRepository,
      PlatformTransactionManager transactionManager) {
    return new OrderServiceTxProxy(new OrderServiceImpl(orderRepository), transactionManager);
  }
  */
}
