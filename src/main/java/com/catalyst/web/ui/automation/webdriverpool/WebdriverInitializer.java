package com.catalyst.web.ui.automation.webdriverpool;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;


@Component("com.catalyst.web.ui.automation.webdriverpool.WebdriverInitializer")
public class WebdriverInitializer {

  //global variable / instance variable static
  public static ChromeDriver driver;

  public void initialize() {
    //download webdriver dan setup
    WebDriverManager.chromedriver().setup();

    //nambahin incognito
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--incognito", "--start-maximized");

    //inisialisasi webdriver
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }

  public void quit() {
    driver.quit();
  }


}
