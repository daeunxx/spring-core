package core.springbasic.singleton;

import static org.assertj.core.api.Assertions.assertThat;
import core.springbasic.AppConfig;
import core.springbasic.member.MemberRepository;
import core.springbasic.member.MemberServiceImpl;
import core.springbasic.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

  @Test
  void configurationTest() {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberServiceImpl memberService = context.getBean(MemberServiceImpl.class);
    OrderServiceImpl orderService = context.getBean(OrderServiceImpl.class);
    MemberRepository memberRepository = context.getBean(MemberRepository.class);

    MemberRepository memberRepository1 = memberService.getMemberRepository();
    MemberRepository memberRepository2 = orderService.getMemberRepository();
    System.out.println("memberService -> memberRepository = " + memberRepository1);
    System.out.println("orderService -> memberRepository = " + memberRepository2);
    System.out.println("memberRepository = " + memberRepository);

    assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
  }

  @Test
  void configurationDeep() {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    AppConfig appConfig = context.getBean(AppConfig.class);

    System.out.println("appConfig = " + appConfig.getClass());
  }
}
