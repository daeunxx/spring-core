package core.springbasic.order;

import static org.assertj.core.api.Assertions.assertThat;

import core.springbasic.discount.FixDiscountPolicy;
import core.springbasic.member.Grade;
import core.springbasic.member.Member;
import core.springbasic.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

  @Test
  void createOrder() {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    memberRepository.save(new Member(1L, "memberA", Grade.VIP));

    OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
    Order order = orderService.createOrder(1L, "itemA", 1000);

    assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }
}