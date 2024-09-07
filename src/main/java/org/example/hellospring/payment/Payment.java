package org.example.hellospring.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class Payment {

  private Long orderId;
  private String currency;
  private BigDecimal foreignCurrencyAmount;
  private BigDecimal exRate;
  private BigDecimal convertedAmount;
  private LocalDateTime validUtil;

  public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate,
      BigDecimal convertedAmount, LocalDateTime validUtil) {
    this.orderId = orderId;
    this.currency = currency;
    this.foreignCurrencyAmount = foreignCurrencyAmount;
    this.exRate = exRate;
    this.convertedAmount = convertedAmount;
    this.validUtil = validUtil;
  }

  public static Payment createPrepared(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate, LocalDateTime now) {
    BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
    LocalDateTime validUntil = now.plusMinutes(30);
    return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
  }

  public boolean isValid(Clock clock) {
    return LocalDateTime.now(clock).isBefore(this.validUtil);
  }

  public Long getOrderId() {
    return orderId;
  }

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getForeignCurrencyAmount() {
    return foreignCurrencyAmount;
  }

  public BigDecimal getExRate() {
    return exRate;
  }

  public BigDecimal getConvertedAmount() {
    return convertedAmount;
  }

  public LocalDateTime getValidUtil() {
    return validUtil;
  }

  @Override
  public String toString() {
    return "Payment{" +
        "orderId=" + orderId +
        ", currency='" + currency + '\'' +
        ", foreignCurrencyAmount=" + foreignCurrencyAmount +
        ", exRate=" + exRate +
        ", convertedAmount=" + convertedAmount +
        ", validUtil=" + validUtil +
        '}';
  }
}
