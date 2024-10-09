package core.springbasic.scope;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototype {

  @Test
  void prototypeFind() {
    ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
        PrototypeBean.class);

    PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
    prototypeBean1.addCount();
    assertThat(prototypeBean1.getCount()).isEqualTo(1);

    PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
    prototypeBean2.addCount();
    assertThat(prototypeBean2.getCount()).isEqualTo(1);
  }

  @Test
  void singletonClientUserPrototype() {
    ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
        PrototypeBean.class, ClientBean.class);

    ClientBean clientBean1 = context.getBean(ClientBean.class);
    ClientBean clientBean2 = context.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    int count2 = clientBean2.logic();
    assertThat(count2).isEqualTo(1);
  }

  @Scope
  @RequiredArgsConstructor
  static class ClientBean {

    private final ObjectProvider<PrototypeBean> provider;

    public int logic() {
      PrototypeBean prototypeBean = provider.getObject();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("prototype")
  static class PrototypeBean {

    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy " + this);
    }
  }
}
