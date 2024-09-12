package org.example.hellospring.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.example.hellospring.payment.ExRateProvider;

public class CachedExRateProvider implements ExRateProvider {

  private final ExRateProvider target;
  private BigDecimal cachedExRate;
  private LocalDateTime cachedExpiryTime;

  public CachedExRateProvider(ExRateProvider target) {
    this.target = target;
  }

  @Override
  public BigDecimal getExRate(String currency) {
    if (cachedExRate == null || cachedExpiryTime.isBefore(LocalDateTime.now())) {
      cachedExRate = target.getExRate(currency);
      cachedExpiryTime = LocalDateTime.now().plusSeconds(3);
      System.out.println("Cache Updated");
    }
    return cachedExRate;
  }
}
