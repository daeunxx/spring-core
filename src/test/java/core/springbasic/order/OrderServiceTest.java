package core.springbasic.order;

import static org.assertj.core.api.Assertions.*;

import core.springbasic.member.Grade;
import core.springbasic.member.Member;
import core.springbasic.member.MemberService;
import core.springbasic.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

  MemberService memberService = new MemberServiceImpl();
  OrderService orderService = new OrderServiceImpl();

  @Test
  void createOrder() {
    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(member.getId(), "itemA", 10000);
    assertThat(order.getDiscountPrice()).isEqualTo(1000);
    assertThat(order.calculatePrice()).isEqualTo(9000);
  }

}
