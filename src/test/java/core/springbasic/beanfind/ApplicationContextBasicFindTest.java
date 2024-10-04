package core.springbasic.beanfind;

import static org.assertj.core.api.Assertions.*;

import core.AppConfig;
import core.springbasic.member.MemberService;
import core.springbasic.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
      AppConfig.class);

  @Test
  @DisplayName("빈 이름으로 조회")
  void findBeanByName() {
    MemberService memberService = context.getBean("memberService", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("구체 타입으로 조회")
  void findBeanByName2() {
    MemberService memberService = context.getBean("memberService", MemberServiceImpl.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("빈 이름으로 조회 불가")
  void findBeanByNameX() {
    assertThatThrownBy(() -> context.getBean("xxxxx", MemberService.class)).
        isInstanceOf(NoSuchBeanDefinitionException.class);
  }

  @Test
  @DisplayName("빈 이름없이 타입으로 조회")
  void findBeanByType() {
    MemberService memberService = context.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }
}
