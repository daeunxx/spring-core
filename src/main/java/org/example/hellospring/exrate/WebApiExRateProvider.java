package org.example.hellospring.exrate;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.example.hellospring.api.ApiTemplate;
import org.example.hellospring.api.ErApiExRateExtractor;
import org.example.hellospring.api.HttpClientApiExecutor;
import org.example.hellospring.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

  ApiTemplate apiTemplate = new ApiTemplate();

  @Override
  public BigDecimal getExRate(String currency) {
    String url = "https://open.er-api.com/v6/latest/" + currency;
    return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
  }
}
