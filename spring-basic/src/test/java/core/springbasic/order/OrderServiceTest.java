package core.springbasic.order;

import static org.assertj.core.api.Assertions.*;

import core.springbasic.AppConfig;
import core.springbasic.member.Grade;
import core.springbasic.member.Member;
import core.springbasic.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

  MemberService memberService;
  OrderService orderService;

  @BeforeEach
  public void setUp() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
    orderService = appConfig.orderService();
  }

  @Test
  void createOrder() {
    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(member.getId(), "itemA", 10000);
    assertThat(order.getDiscountPrice()).isEqualTo(1000);
    assertThat(order.calculatePrice()).isEqualTo(9000);
  }

}
