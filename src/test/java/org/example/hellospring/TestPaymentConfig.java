package org.example.hellospring;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.example.hellospring.payment.ExRateProvider;
import org.example.hellospring.payment.ExRateProviderStub;
import org.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class TestPaymentConfig {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(exRateProvider(), clock());
  }

  @Bean
  public ExRateProvider exRateProvider() {
    return new ExRateProviderStub(BigDecimal.valueOf(1_000));
  }

  @Bean
  public Clock clock() {
    return Clock.fixed(Instant.now(), ZoneId.systemDefault());
  }
}
