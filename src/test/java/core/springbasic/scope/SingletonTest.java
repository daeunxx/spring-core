package core.springbasic.scope;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

  @Test
  void singletonBeanFind() {
    ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SingletonBean.class);
    SingletonBean bean1 = context.getBean(SingletonBean.class);
    SingletonBean bean2 = context.getBean(SingletonBean.class);
    assertThat(bean1).isSameAs(bean2);
    context.close();
  }

  // 디폴트 설정
  @Scope("singleton")
  static class SingletonBean {

    @PostConstruct
    public void init() {
      System.out.println("SingletonBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("SingletonBean.destroy");
    }
  }
}
