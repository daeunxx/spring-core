package core;

import core.springbasic.discount.DiscountPolicy;
import core.springbasic.discount.FixDiscountPolicy;
import core.springbasic.member.MemberRepository;
import core.springbasic.member.MemberService;
import core.springbasic.member.MemberServiceImpl;
import core.springbasic.member.MemoryMemberRepository;
import core.springbasic.order.OrderService;
import core.springbasic.order.OrderServiceImpl;

public class AppConfig {

  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  private MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  private DiscountPolicy discountPolicy() {
    return new FixDiscountPolicy();
  }

}
