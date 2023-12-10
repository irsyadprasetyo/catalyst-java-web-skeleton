package com.catalyst.web.ui.automation.webdriverpool;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Data
@ScenarioScope
@Component("com.catalyst.web.ui.automation.webdriverpool.WebDriverPool")
public class WebDriverPool {

  private Webdrivers drivers = new Webdrivers();
  private String scenarioName;

  @Data
  public static class Webdrivers {

    //should be multiple webdriver here.
    private WebDriver webDriver;
    private WebDriver iosDriver;
    private WebDriver androidDriver;

  }

}
