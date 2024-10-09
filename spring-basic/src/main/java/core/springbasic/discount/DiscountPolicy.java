package core.springbasic.discount;

import core.springbasic.member.Member;

public interface DiscountPolicy {

  int discount(Member member, int price);
}
