package core.springbasic;

import core.springbasic.member.Grade;
import core.springbasic.member.Member;
import core.springbasic.member.MemberService;
import core.springbasic.member.MemberServiceImpl;
import core.springbasic.order.Order;
import core.springbasic.order.OrderService;
import core.springbasic.order.OrderServiceImpl;

public class OrderApp {

  public static void main(String[] args) {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(member.getId(), "itemA", 10000);

    System.out.println("order = " + order);
    System.out.println("order.calculatePrice = " + order.calculatePrice());
  }
}
