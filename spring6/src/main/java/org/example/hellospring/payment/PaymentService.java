package org.example.hellospring.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class PaymentService {

  private final ExRateProvider exRateProvider;
  private final Clock clock;

  public PaymentService(ExRateProvider exRateProvide, Clock clock) {
    this.exRateProvider = exRateProvide;
    this.clock = clock;
  }

  public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {
    BigDecimal exRate = exRateProvider.getExRate(currency);
    return Payment.createPrepared(orderId, currency, foreignCurrencyAmount, exRate,
        LocalDateTime.now(clock));
  }
}