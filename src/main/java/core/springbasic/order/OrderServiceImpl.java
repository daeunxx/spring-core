package core.springbasic.order;

import core.springbasic.discount.DiscountPolicy;
import core.springbasic.discount.FixDiscountPolicy;
import core.springbasic.discount.RateDiscountPolicy;
import core.springbasic.member.Member;
import core.springbasic.member.MemberRepository;
import core.springbasic.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

  private MemberRepository memberRepository;
  private DiscountPolicy discountPolicy;

  @Autowired
  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    System.out.println("OrderServiceImpl.OrderServiceImpl");
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Autowired
  public void setMemberRepository(MemberRepository memberRepository) {
    System.out.println("OrderServiceImpl.setMemberRepository");
    this.memberRepository = memberRepository;
  }

  @Autowired
  public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    System.out.println("OrderServiceImpl.setDiscountPolicy");
    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);
    return new Order(memberId, itemName, itemPrice, discountPrice);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
