package core;

import core.springbasic.discount.FixDiscountPolicy;
import core.springbasic.member.MemberService;
import core.springbasic.member.MemberServiceImpl;
import core.springbasic.member.MemoryMemberRepository;
import core.springbasic.order.OrderService;
import core.springbasic.order.OrderServiceImpl;

public class AppConfig {

  public MemberService memberService() {
    return new MemberServiceImpl(new MemoryMemberRepository());
  }

  public OrderService orderService() {
    return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
  }

}
