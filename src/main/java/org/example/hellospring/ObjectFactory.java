package org.example.hellospring;

import org.example.hellospring.exrate.CachedExRateProvider;
import org.example.hellospring.payment.ExRateProvider;
import org.example.hellospring.exrate.WebApiExRateProvider;
import org.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ObjectFactory {

  //싱글톤으로 만들어짐
  @Bean
  public PaymentService paymentService() {
    return new PaymentService(cachedExRateProvider());
  }

  @Bean
  public ExRateProvider cachedExRateProvider() {
    return new CachedExRateProvider(exRateProvider());
  }

  @Bean
  public ExRateProvider exRateProvider() {
    return new WebApiExRateProvider();
  }
}
