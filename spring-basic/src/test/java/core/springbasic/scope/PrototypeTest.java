package core.springbasic.scope;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

  @Test
  void prototypeBeanFind() {
    ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);

    System.out.println("Find prototypeBean1");
    PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);

    System.out.println("Find prototypeBean2");
    PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);

    System.out.println("prototypeBean1 = " + prototypeBean1);
    System.out.println("prototypeBean2 = " + prototypeBean2);
    assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

    // 직접 @PreDestroy 실행해주어야 함
    prototypeBean1.destroy();
    prototypeBean2.destroy();

    context.close();
  }

  @Scope("prototype")
  static class PrototypeBean {

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}
