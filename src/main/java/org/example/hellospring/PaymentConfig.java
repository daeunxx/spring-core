package org.example.hellospring;

import java.time.Clock;
import org.example.hellospring.api.ApiTemplate;
import org.example.hellospring.api.ErApiExRateExtractor;
import org.example.hellospring.api.SimpleApiExecutor;
import org.example.hellospring.exrate.CachedExRateProvider;
import org.example.hellospring.exrate.RestTemplateExRateProvider;
import org.example.hellospring.payment.ExRateProvider;
import org.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
public class PaymentConfig {

  //싱글톤으로 만들어짐
  @Bean
  public PaymentService paymentService() {
    return new PaymentService(cachedExRateProvider(), clock());
  }

  @Bean
  public ApiTemplate apiTemplate() {
    return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
  }

  @Bean
  public ExRateProvider exRateProvider() {
    //return new WebApiExRateProvider(apiTemplate());
    return new RestTemplateExRateProvider(restTemplate());
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate(new JdkClientHttpRequestFactory());
  }

  @Bean
  public ExRateProvider cachedExRateProvider() {
    return new CachedExRateProvider(exRateProvider());
  }

  @Bean
  public Clock clock() {
    return Clock.systemDefaultZone();
  }
}
