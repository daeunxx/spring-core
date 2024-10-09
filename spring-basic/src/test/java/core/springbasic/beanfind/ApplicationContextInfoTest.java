package core.springbasic.beanfind;

import core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("모든 빈 출력")
  void findAllBean() {
    String[] beanDefinitionNames = context.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      Object bean = context.getBean(beanDefinitionName);
      System.out.println("name = " + beanDefinitionName + ", bean = " + bean);
    }
  }

  @Test
  @DisplayName("애플리케이션 빈 출력")
  void findApplicationBean() {
    String[] beanDefinitionNames = context.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      BeanDefinition beanDefinition = context.getBeanDefinition(beanDefinitionName);

      // BeanDefinition.ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
      // BeanDefinition.INFRASTRUCTURE: 스프링 내부 사용 빈
      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        Object bean = context.getBean(beanDefinitionName);
        System.out.println("name = " + beanDefinitionName + ", bean = " + bean);
      }
    }
  }
}
