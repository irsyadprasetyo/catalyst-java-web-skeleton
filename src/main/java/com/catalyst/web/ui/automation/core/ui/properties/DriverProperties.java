package com.catalyst.web.ui.automation.core.ui.properties;


import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ScenarioScope
@ConfigurationProperties(prefix = "driver")
public class DriverProperties {

  private WebdriverProperties web;
  private MobileDriverProperties mobile;

}
