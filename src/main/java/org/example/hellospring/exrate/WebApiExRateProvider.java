package org.example.hellospring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import org.example.hellospring.api.ApiExecutor;
import org.example.hellospring.api.SimpleApiExecutor;
import org.example.hellospring.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

  @Override
  public BigDecimal getExRate(String currency) {
    String url = "https://open.er-api.com/v6/latest/" + currency;
    return runApiForExRate(url, new SimpleApiExecutor());
  }

  private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor) {
    URI uri;
    try {
      uri = new URI(url);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

    String response;
    try {
      response = apiExecutor.execute(uri);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      return extractExRate(response);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
  private static BigDecimal extractExRate(String response) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    ExRateData data = mapper.readValue(response, ExRateData.class);
    return data.rates().get("KRW");
  }
}
