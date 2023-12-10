package com.catalyst.web.ui.automation.core.ui.webdriver.browsers;

import com.catalyst.web.ui.automation.core.ui.webdriver.Browser;
import com.catalyst.web.ui.automation.core.ui.properties.WebdriverProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.HashMap;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;


@Slf4j
public class InternetExplorer implements Browser {

  private InternetExplorerOptions internetExplorerOptions;

  public InternetExplorer() {
    this.internetExplorerOptions = new InternetExplorerOptions();
    setupWebdriverManager();
  }

  @Override
  public WebDriver initialize() {
    try {
      return new InternetExplorerDriver(internetExplorerOptions);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void setCapabilities(WebdriverProperties.BrowserProperties browserProperties) {
    log.info("Adding capabilities...");
    //adding arguments
    Optional.ofNullable(browserProperties.getArguments())
        .ifPresent(args -> args.forEach(arg -> internetExplorerOptions.addCommandSwitches(arg)));

    //adding capabilities
    Optional.ofNullable(browserProperties.getCapabilities())
        .ifPresent(args -> args.forEach((k, v) -> internetExplorerOptions.setCapability(k, v)));
    Optional.ofNullable(browserProperties.getNumberCapabilities())
        .ifPresent(args -> args.forEach((k, v) -> internetExplorerOptions.setCapability(k, v)));
  }

  @Override
  public void setHeadless() {
    log.info("IE Doesn't support headless mode");
  }

  @Override
  public void setPreference(HashMap<String, String> prefs) {

  }

  @Override
  public void setupWebdriverManager() {
    try {
      WebDriverManager.iedriver().timeout(3).setup();
    } catch (Exception e) {
      log.error("failed to download IE driver. retrying uisng mirror server...");
      try {
        WebDriverManager.iedriver().timeout(10).useMirror().setup();
      } catch (Exception ex) {
        log.error("Failed to download IE driver Using Mirror Server.");
      }
    }
  }
}
