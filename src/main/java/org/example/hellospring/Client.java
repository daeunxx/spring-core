package org.example.hellospring;

import java.io.IOException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

  public static void main(String[] args) throws IOException {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
    PaymentService paymentService1 = beanFactory.getBean(PaymentService.class);
    PaymentService paymentService2 = beanFactory.getBean(PaymentService.class);
    OrderService orderService = beanFactory.getBean(OrderService.class);

    System.out.println("paymentService1 = " + paymentService1);
    System.out.println("paymentService2 = " + paymentService2);
    System.out.println("(paymentService1 == paymentService2): " + 
        (paymentService1 == paymentService2));

    ObjectFactory objectFactory = beanFactory.getBean(ObjectFactory.class);
    PaymentService objPaymentService1 = objectFactory.paymentService();
    PaymentService objPaymentService2 = objectFactory.paymentService();
    System.out.println("(objPaymentService1 == objPaymentService2): " + 
        (objPaymentService1 == objPaymentService2));


    Payment payment = paymentService1.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
    System.out.println("payment = " + payment);
  }
}
