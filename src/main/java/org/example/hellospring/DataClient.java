package org.example.hellospring;

import java.math.BigDecimal;
import org.example.hellospring.data.OrderRepository;
import org.example.hellospring.order.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DataClient {

  public static void main(String[] args) {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
    OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);

    Order order = new Order("100", BigDecimal.TEN);
    orderRepository.save(order);

    System.out.println("order = " + order);

    Order order2 = new Order("100", BigDecimal.TEN);
    orderRepository.save(order2);
  }
}
