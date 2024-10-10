package core.springbasic;

import core.springbasic.member.Grade;
import core.springbasic.member.Member;
import core.springbasic.member.MemberService;
import core.springbasic.order.Order;
import core.springbasic.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = context.getBean("memberService", MemberService.class);
    OrderService orderService = context.getBean("orderService", OrderService.class);

    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(member.getId(), "itemA", 20000);

    System.out.println("order = " + order);
    System.out.println("order.calculatePrice = " + order.calculatePrice());
  }
}
