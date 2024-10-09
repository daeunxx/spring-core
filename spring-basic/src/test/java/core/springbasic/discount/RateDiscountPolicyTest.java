package core.springbasic.discount;

import static org.assertj.core.api.Assertions.*;

import core.springbasic.member.Grade;
import core.springbasic.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

  RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP 10프로 할인 적용")
  void vip_o() {
    // given
    Member member = new Member(1L, "memberVIP", Grade.VIP);
    // when
    int discount = rateDiscountPolicy.discount(member, 10000);
    // then
    assertThat(discount).isEqualTo(1000);
  }

  @Test
  @DisplayName("VIP 아닌 경우, 할인 없음")
  void vip_x() {
    // given
    Member member = new Member(1L, "memberBASOC", Grade.BASIC);
    // when
    int discount = rateDiscountPolicy.discount(member, 10000);
    // then
    assertThat(discount).isEqualTo(0);
  }
}