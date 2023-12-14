package com.catalyst.web.ui.automation.data;

import io.cucumber.spring.ScenarioScope;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Data
@ScenarioScope
@Component("com.catalyst.web.ui.automation.data.MwebPropertiesData")
public class MwebPropertiesData {

  public Boolean runMweb;

  // Enable mobile emulation
//
//  // Create WebDriver instance with ChromeOptions
//  WebDriver driver = new ChromeDriver(options);
//    pageObject.setDriver(driver);

}
