package org.example.hellospring.payment;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

  @Test
  @DisplayName("prepare 메소드 요구사항 3가지 충족 검증")
  void convertedAmount() throws IOException {
    testAmount(valueOf(500), valueOf(5_000));
    testAmount(valueOf(1_000), valueOf(10_000));
    testAmount(valueOf(3_000), valueOf(30_000));

    // 원화 환산 금액 유효 시간
    //assertThat(payment.getValidUtil()).isAfter(LocalDateTime.now());
    //assertThat(payment.getValidUtil()).isBefore(LocalDateTime.now().plusMinutes(30));
  }

  private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
    PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));
    Payment payment = paymentService.prepare(1L, "USD", TEN);

    // 환율 정보
    assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

    // 원화 환산 금액
    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
  }
}