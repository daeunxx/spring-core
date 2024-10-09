package core.springbasic.scan;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import core.springbasic.AutoAppConfig;
import core.springbasic.member.MemberService;
import core.springbasic.member.MemberServiceImpl;
import core.springbasic.order.OrderService;
import core.springbasic.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

  @Test
  void basicScan() {
    ApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    MemberService memberService = context.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    OrderService orderService = context.getBean(OrderService.class);
    assertThat(orderService).isInstanceOf(OrderServiceImpl.class);
  }
}
