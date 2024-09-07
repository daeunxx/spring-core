package org.example.hellospring.payment;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PaymentTest {

  Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

  @Test
  void createPrepared() {
    Payment payment = Payment.createPrepared(1L, "USD", TEN, valueOf(1_000), LocalDateTime.now(clock));

    Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));
    Assertions.assertThat(payment.getValidUtil())
        .isEqualTo(LocalDateTime.now(clock).plusMinutes(30));

  }
}