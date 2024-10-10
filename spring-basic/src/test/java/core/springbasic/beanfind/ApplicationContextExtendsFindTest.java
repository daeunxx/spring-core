package core.springbasic.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import core.springbasic.discount.DiscountPolicy;
import core.springbasic.discount.FixDiscountPolicy;
import core.springbasic.discount.RateDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsFindTest {

  ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

  @Test
  @DisplayName("부모 타입으로 조회, 자식이 둘 이상 존재하면 중복 오류")
  void findBeanByParentTypeDuplicate() {
    assertThatThrownBy(() -> context.getBean(DiscountPolicy.class))
        .isInstanceOf(NoUniqueBeanDefinitionException.class);
  }

  @Test
  @DisplayName("부모 타입으로 조회, 빈 이름 지정")
  void findBeanByParentTypeBeanName() {
    DiscountPolicy rateDiscountPolicy = context.getBean("rateDiscountPolicy", DiscountPolicy.class);
    DiscountPolicy fixedDiscountPolicy = context.getBean("fixedDiscountPolicy", DiscountPolicy.class);

    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    assertThat(fixedDiscountPolicy).isInstanceOf(FixDiscountPolicy.class);
  }

  @Test
  @DisplayName("특정 하위 타입으로 조회")
  void findBeanBySubType() {
    RateDiscountPolicy rateDiscountPolicy = context.getBean(RateDiscountPolicy.class);
    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회")
  void findAllBeanByParentType() {
    Map<String, DiscountPolicy> beansOfType = context.getBeansOfType(DiscountPolicy.class);
    assertThat(beansOfType.size()).isEqualTo(2);

    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + ", bean = " + beansOfType.get(key));
    }
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회 - Object")
  void findAllBeanByObjectType() {
    Map<String, Object> beansOfType = context.getBeansOfType(Object.class);

    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + ", bean = " + beansOfType.get(key));
    }
  }

  @Configuration
  static class TestConfig {
    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }

    @Bean DiscountPolicy fixedDiscountPolicy() {
      return new FixDiscountPolicy();
    }
  }
}
