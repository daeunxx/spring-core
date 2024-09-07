package org.example.hellospring;

import java.math.BigDecimal;
import org.example.hellospring.order.Order;
import org.example.hellospring.order.OrderService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderClient {

  public static void main(String[] args) {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
    OrderService orderService = beanFactory.getBean(OrderService.class);

    Order order = orderService.createOrder("0100", BigDecimal.TEN);
    System.out.println("order = " + order);
  }
}
