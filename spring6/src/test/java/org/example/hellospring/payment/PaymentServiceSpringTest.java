package org.example.hellospring.payment;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.example.hellospring.TestPaymentConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

  @Autowired
  PaymentService paymentService;

  @Autowired
  ExRateProviderStub exRateProviderStub;

  @Autowired
  Clock clock;

  @Test
  @DisplayName("환율 정보, 원화 환산 금액 검증")
  void convertedAmount() {
    // exRate: 1000
    Payment payment = paymentService.prepare(1L, "USD", TEN);
    assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));
    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

    // exRate: 500
    exRateProviderStub.setExRate(valueOf(500));
    Payment payment2 = paymentService.prepare(1L, "USD", TEN);
    assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
    assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
  }

  @Test
  @DisplayName("원화 환산 금액 유효 시간 검증")
  void validUtil() {
    Payment payment = paymentService.prepare(1L, "USD", TEN);

    LocalDateTime now = LocalDateTime.now(this.clock);
    LocalDateTime expectedValidUntil = now.plusMinutes(30);

    Assertions.assertThat(payment.getValidUtil()).isEqualTo(expectedValidUntil);
  }
}