package org.example.hellospring;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

  public static void main(String[] args) throws IOException, InterruptedException {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
    PaymentService paymentService = beanFactory.getBean(PaymentService.class);

    Payment payment1 = paymentService.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
    System.out.println("payment1 = " + payment1);
    System.out.println("--------------------------------\n");

    Payment payment2 = paymentService.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
    System.out.println("payment2 = " + payment2);
    System.out.println("--------------------------------\n");

    TimeUnit.SECONDS.sleep(3);
    Payment payment3 = paymentService.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
    System.out.println("payment3 = " + payment3);
  }
}
