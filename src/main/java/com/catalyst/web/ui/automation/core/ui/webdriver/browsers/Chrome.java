package com.catalyst.web.ui.automation.core.ui.webdriver.browsers;

import com.catalyst.web.ui.automation.core.ui.properties.WebdriverProperties.BrowserProperties;
import com.catalyst.web.ui.automation.core.ui.webdriver.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
public class Chrome implements Browser {

  private final ChromeOptions chromeOptions = new ChromeOptions();

  public Chrome() {
    //    System.setProperty("webdriver.chrome.driver",
    //        System.getProperty("user.dir") + "/cft/chromedriver");
    // chromeOptions.addArguments("--remote-allow-origin");
    setupWebdriverManager();
  }

  @Override
  public WebDriver initialize() {
    try {
      return new ChromeDriver();
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void setCapabilities(BrowserProperties browserProperties) {
    //adding arguments
    Optional.ofNullable(browserProperties.getArguments())
        .ifPresent(args -> args.forEach(arg -> chromeOptions.addArguments(arg)));

    //adding capabilities
    Optional.ofNullable(browserProperties.getCapabilities())
        .ifPresent(args -> args.forEach((k, v) -> chromeOptions.setCapability(k, v)));
    Optional.ofNullable(browserProperties.getNumberCapabilities())
        .ifPresent(args -> args.forEach((k, v) -> chromeOptions.setCapability(k, v)));
  }

  @Override
  public void setHeadless() {
    chromeOptions.addArguments("--headless");
  }

  @Override
  public void setPreference(HashMap<String, String> prefs) {
    log.info("Adding prefs...");
    Map<String, Object> pref = new HashMap<>();
    pref.put("download.default_directory",
        System.getProperty("user.dir") + File.separator + prefs.get("download"));
    this.chromeOptions.setExperimentalOption("prefs", pref);
  }

  @Override
  public void setupWebdriverManager() {
    try {
      WebDriverManager.chromedriver().clearResolutionCache();
      WebDriverManager.chromedriver().setup();
    } catch (Exception e) {
      log.error("Failed to download chromedriver Using Mirror Server....");
      try {
        WebDriverManager.chromedriver().useMirror().setup();
      } catch (Exception ex) {
        log.error("Failed to download chromedriver Using Mirror Server....");
      }
    }
  }
}
