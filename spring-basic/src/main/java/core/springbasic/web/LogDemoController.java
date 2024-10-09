package core.springbasic.web;

import core.springbasic.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

  private final LogDemoService logDemoService;
  private final MyLogger myLogger;

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) {
    String requestURI = request.getRequestURI();

    System.out.println("myLogger = " + myLogger.getClass());
    myLogger.setRequestURL(requestURI);
    myLogger.log("controller test");

    logDemoService.logic("testId");

    return "OK";
  }
}