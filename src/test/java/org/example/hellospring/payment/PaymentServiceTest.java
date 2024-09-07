package org.example.hellospring.payment;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

  Clock clock;

  @BeforeEach
  void init() {
    this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
  }

  @Test
  @DisplayName("환율 정보, 원화 환산 금액 검증")
  void convertedAmount() {
    testAmount(valueOf(500), valueOf(5_000), this.clock);
    testAmount(valueOf(1_000), valueOf(10_000), this.clock);
    testAmount(valueOf(3_000), valueOf(30_000), this.clock);
  }

  @Test
  @DisplayName("원화 환산 금액 유효 시간 검증")
  void validUtil() {
    PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
    Payment payment = paymentService.prepare(1L, "USD", TEN);

    LocalDateTime now = LocalDateTime.now(this.clock);
    LocalDateTime expectedValidUntil = now.plusMinutes(30);

    assertThat(payment.getValidUtil()).isEqualTo(expectedValidUntil);
  }

  private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
    PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);
    Payment payment = paymentService.prepare(1L, "USD", TEN);

    assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
  }

  @Test
  public void isValid() {
    Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    Payment payment = Payment.createPrepared(1L, "USD", TEN, valueOf(1_000), LocalDateTime.now(clock));
    assertThat(payment.isValid(clock));
    Assertions.assertThat(payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES)))).isFalse();
  }
}