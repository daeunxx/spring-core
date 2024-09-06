package org.example.hellospring.payment;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.example.hellospring.TestObjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {

  @Autowired
  PaymentService paymentService;

  @Autowired
  ExRateProviderStub exRateProviderStub;

  @Test
  @DisplayName("prepare 메소드 요구사항 3가지 충족 검증")
  void convertedAmount() throws IOException {
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
}