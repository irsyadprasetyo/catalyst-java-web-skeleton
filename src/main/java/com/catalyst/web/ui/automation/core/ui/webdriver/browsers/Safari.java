package com.catalyst.web.ui.automation.core.ui.webdriver.browsers;

import com.catalyst.web.ui.automation.core.ui.webdriver.Browser;
import com.catalyst.web.ui.automation.core.ui.properties.WebdriverProperties.BrowserProperties;
import java.util.HashMap;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

@Slf4j
public class Safari implements Browser {

  SafariOptions safariOptions;

  public Safari() {
    safariOptions = new SafariOptions();
    setupWebdriverManager();
  }

  @Override
  public WebDriver initialize() {
    try {
      return new SafariDriver(safariOptions);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void setCapabilities(BrowserProperties browserProperties) {
    log.info("Adding capabilities...");
    //adding capabilities
    Optional.ofNullable(browserProperties.getCapabilities())
        .ifPresent(args -> args.forEach((k, v) -> safariOptions.setCapability(k, v)));
    Optional.ofNullable(browserProperties.getNumberCapabilities())
        .ifPresent(args -> args.forEach((k, v) -> safariOptions.setCapability(k, v)));
  }

  @Override
  public void setHeadless() {
    log.info("Safari doesn't support headless mode");
  }

  @Override
  public void setPreference(HashMap<String, String> prefs) {

  }

  @Override
  public void setupWebdriverManager() {
    log.info("Webdriver manager doesn't support safari webdriver. you should set up it manually");
  }
}
