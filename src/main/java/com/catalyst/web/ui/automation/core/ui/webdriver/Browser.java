package com.catalyst.web.ui.automation.core.ui.webdriver;

import com.catalyst.web.ui.automation.core.ui.properties.WebdriverProperties.BrowserProperties;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;

public interface Browser {

  WebDriver initialize();

  void setCapabilities(BrowserProperties browserProperties);

  void setHeadless();

  void setPreference(HashMap<String, String> prefs);

  void setupWebdriverManager();
}
