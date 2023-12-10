package com.catalyst.web.ui.automation.core.ui.webdriver.browsers;

import com.catalyst.web.ui.automation.core.ui.properties.WebdriverProperties.BrowserProperties;
import com.catalyst.web.ui.automation.core.ui.webdriver.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.HashMap;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Slf4j
public class Firefox implements Browser {

  private FirefoxOptions firefoxOptions;

  public Firefox() {
    firefoxOptions = new FirefoxOptions();
    setupWebdriverManager();
  }

  @Override
  public WebDriver initialize() {
    try {
      return new FirefoxDriver(firefoxOptions);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void setCapabilities(BrowserProperties browserProperties) {
    log.info("Adding capabilities...");
    //adding arguments
    Optional.ofNullable(browserProperties.getArguments())
        .ifPresent(args -> args.forEach(arg -> firefoxOptions.addArguments(arg)));

    //adding capabilities
    Optional.ofNullable(browserProperties.getCapabilities())
        .ifPresent(args -> args.forEach((k, v) -> firefoxOptions.setCapability(k, v)));
    Optional.ofNullable(browserProperties.getNumberCapabilities())
        .ifPresent(args -> args.forEach((k, v) -> firefoxOptions.setCapability(k, v)));
  }

  @Override
  public void setHeadless() {
    firefoxOptions.addArguments("--headless");
  }

  @Override
  public void setPreference(HashMap<String, String> prefs) {

  }

  @Override
  public void setupWebdriverManager() {
    try {
      WebDriverManager.firefoxdriver().timeout(3).setup();
    } catch (Exception e) {
      log.error("failed to download firefox driver. retrying uisng mirror server...");
      try {
        WebDriverManager.firefoxdriver().timeout(10).useMirror().setup();
      } catch (Exception ex) {
        log.error("Failed to download Firefox driver Using Mirror Server.");
      }
    }
  }
}
