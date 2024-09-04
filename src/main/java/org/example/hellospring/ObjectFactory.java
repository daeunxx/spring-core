package org.example.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ObjectFactory {

  //싱글톤으로 만들어짐
  @Bean
  public PaymentService paymentService() {
    return new PaymentService(exRateProvider());
  }

  @Bean
  public OrderService orderService() {
    return new OrderService(exRateProvider());
  }

  @Bean
  public ExRateProvider exRateProvider() {
    return new WebApiExRateProvider();
  }
}

class OrderService {

  ExRateProvider exRateProvider;

  public OrderService(ExRateProvider exRateProvider) {
    this.exRateProvider = exRateProvider;
  }
}
