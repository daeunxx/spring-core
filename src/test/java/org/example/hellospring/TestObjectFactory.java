package org.example.hellospring;

import java.math.BigDecimal;
import org.example.hellospring.payment.ExRateProvider;
import org.example.hellospring.payment.ExRateProviderStub;
import org.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class TestObjectFactory {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(exRateProvider());
  }

  @Bean
  public ExRateProvider exRateProvider() {
    return new ExRateProviderStub(BigDecimal.valueOf(1_000));
  }
}
