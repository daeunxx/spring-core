package org.example.hellospring.payment;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.example.hellospring.exrate.WebApiExRateProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

  @Test
  @DisplayName("prepare 메소드 요구사항 3가지 충족 검증")
  void prepare() throws IOException {
    PaymentService paymentService = new PaymentService(new WebApiExRateProvider());
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    // 환율 정보
    assertThat(payment.getExRate()).isNotNull();

    // 원화 환산 금액
    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(
        payment.getExRate().multiply(payment.getForeignCurrencyAmount()));

    // 원화 환산 금액 유효 시간
    assertThat(payment.getValidUtil()).isAfter(LocalDateTime.now());
    assertThat(payment.getValidUtil()).isBefore(LocalDateTime.now().plusMinutes(30));
  }
}