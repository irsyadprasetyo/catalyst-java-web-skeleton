package com.catalyst.web.ui.automation.hooks;

import com.catalyst.web.ui.automation.core.ui.pageobject.PageObject;
import com.catalyst.web.ui.automation.webdriverpool.WebDriverPool;
import com.catalyst.web.ui.automation.webdriverpool.WebDriverPool.Webdrivers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class WebdriverHook {

  @Autowired
  PageObject pageObject;

  @Autowired
  WebDriverPool webdriverPool;

  @After(order = 0)
  public void afterEachScenario(Scenario scenario) {
    System.out.println("Quitting driver for user " + Thread.currentThread().getName());
    quitWebdrivers(scenario, pageObject.getWebdriverPool().getDrivers());
  }

  @Before(order = 0)
  public void beforeEachScenario(Scenario scenario) {
    webdriverPool.setScenarioName(scenario.getName());
  }

  private void quitWebdrivers(Scenario scenario, Webdrivers drivers) {
    Optional.ofNullable(drivers.getWebDriver()).ifPresent(driver -> {
      screenShot(scenario, driver);
      driver.quit();
    });

    Optional.ofNullable(drivers.getIosDriver()).ifPresent(driver -> {
      screenShot(scenario, driver);
      driver.quit();
    });

    Optional.ofNullable(drivers.getAndroidDriver()).ifPresent(driver -> {
      screenShot(scenario, driver);
      driver.quit();
    });
  }

  private void screenShot(Scenario scenario, WebDriver driver) {
    if (scenario.isFailed()) {
      TakesScreenshot screenshot = (TakesScreenshot) driver;
      byte[] imageByte = screenshot.getScreenshotAs(OutputType.BYTES);
      scenario.attach(imageByte, "image/png", scenario.getId());
      scenario.log("Scenario Fail");
    }

    String pageSource = driver.getPageSource();
    File directory = new File("target");
    if (!directory.exists()) {
      directory.mkdir();
    }

    File file = new File("target/" + scenario.getName() + ".xml");

    try {
      Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
      PrintWriter pw = new PrintWriter(writer);
      pw.println(pageSource);
      pw.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
