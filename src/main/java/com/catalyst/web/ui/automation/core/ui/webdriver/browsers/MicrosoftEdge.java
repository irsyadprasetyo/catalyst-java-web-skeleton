package com.catalyst.web.ui.automation.core.ui.webdriver.browsers;

import com.catalyst.web.ui.automation.core.ui.webdriver.Browser;
import com.catalyst.web.ui.automation.core.ui.properties.WebdriverProperties.BrowserProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.HashMap;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

@Slf4j
public class MicrosoftEdge implements Browser {

  private EdgeOptions edgeOptions;

  public MicrosoftEdge() {
    this.edgeOptions = new EdgeOptions();
    setupWebdriverManager();
  }

  @Override
  public WebDriver initialize() {
    try {
      return new EdgeDriver(edgeOptions);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void setCapabilities(BrowserProperties browserProperties) {
    log.info("Adding capabilities...");
    //adding capabilities
    Optional.ofNullable(browserProperties.getCapabilities())
        .ifPresent(args -> args.forEach((k, v) -> edgeOptions.setCapability(k, v)));
    Optional.ofNullable(browserProperties.getNumberCapabilities())
        .ifPresent(args -> args.forEach((k, v) -> edgeOptions.setCapability(k, v)));
  }

  @Override
  public void setHeadless() {
    log.info("Microst Edge doesn't support headless mode");

  }

  @Override
  public void setPreference(HashMap<String, String> prefs) {

  }

  @Override
  public void setupWebdriverManager() {
    try {
      WebDriverManager.edgedriver().timeout(3).setup();
    } catch (Exception e) {
      log.error("failed to download Microsoft Edge driver. retrying uisng mirror server...");
      try {
        WebDriverManager.edgedriver().timeout(10).useMirror().setup();
      } catch (Exception ex) {
        log.error("Failed to download Microsoft Edge driver Using Mirror Server.");
      }
    }
  }
}
