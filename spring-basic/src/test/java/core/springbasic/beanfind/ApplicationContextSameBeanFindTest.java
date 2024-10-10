package core.springbasic.beanfind;

import static org.assertj.core.api.Assertions.*;

import core.springbasic.member.MemberRepository;
import core.springbasic.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest {

  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SameBeanConfig.class);

  @Test
  @DisplayName("타입으로 조회 시, 같은 타입이 둘 이상 존재하면 중복 오류 발생")
  void findBeanByTypeDuplicate() {
    assertThatThrownBy(() -> context.getBean(MemberRepository.class))
        .isInstanceOf(NoUniqueBeanDefinitionException.class);
  }

  @Test
  @DisplayName("타입으로 조회 시, 같은 타입이 둘 이상 존재하면 빈 이름 지정")
  void findBeanByName() {
    MemberRepository memberRepository = context.getBean("memberRepository1", MemberRepository.class);
    assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);
  }

  @Test
  @DisplayName("특정 타입 모두 조회")
  void findAllBeanByType() {
    Map<String, MemberRepository> beansOfType = context.getBeansOfType(MemberRepository.class);
    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + ", bean = " + beansOfType.get(key));
    }
    System.out.println("beansOfType = " + beansOfType);
    assertThat(beansOfType).hasSize(2);
  }

  @Configuration
  static class SameBeanConfig {

    @Bean
    public MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }

    @Bean MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }
  }
}
