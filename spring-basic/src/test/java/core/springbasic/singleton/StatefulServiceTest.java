package core.springbasic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

  @Test
  void statefulServiceSingleton() {
    ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = context.getBean(StatefulService.class);
    StatefulService statefulService2 = context.getBean(StatefulService.class);

    statefulService1.order("userA", 10000);
    statefulService2.order("userB", 20000);

    int userAPrice = statefulService1.getPrice();
    int userBPrice = statefulService2.getPrice();

    System.out.println("userAPrice = " + userAPrice);
    System.out.println("userBPrice = " + userBPrice);

    Assertions.assertThat(statefulService1.getPrice()).isEqualTo(userBPrice);
  }

  static class TestConfig {
    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }
}