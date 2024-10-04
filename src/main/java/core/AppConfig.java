package core;

import core.springbasic.discount.DiscountPolicy;
import core.springbasic.discount.RateDiscountPolicy;
import core.springbasic.member.MemberRepository;
import core.springbasic.member.MemberService;
import core.springbasic.member.MemberServiceImpl;
import core.springbasic.member.MemoryMemberRepository;
import core.springbasic.order.OrderService;
import core.springbasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    //return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }

}
