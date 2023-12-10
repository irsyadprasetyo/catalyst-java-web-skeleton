package com.catalyst.web.ui.automation.core.ui.webdriver;

import com.catalyst.web.ui.automation.core.ui.properties.DriverProperties;
import com.catalyst.web.ui.automation.core.ui.webdriver.browsers.Chrome;
import com.catalyst.web.ui.automation.core.ui.webdriver.browsers.Firefox;
import com.catalyst.web.ui.automation.core.ui.webdriver.browsers.InternetExplorer;
import com.catalyst.web.ui.automation.core.ui.webdriver.browsers.MicrosoftEdge;
import com.catalyst.web.ui.automation.core.ui.webdriver.browsers.Safari;
import com.catalyst.web.ui.automation.webdriverpool.WebDriverPool;
import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component("com.catalyst.web.ui.automation.core.ui.webdriver.WebdriverCreator")
public class WebdriverCreator {

  private final DriverProperties driverProperties;

  private final WebDriverPool webdriverPool;

  private Browser browser;

  private SessionId remoteSessionId;

  public WebdriverCreator(DriverProperties driverProperties, WebDriverPool webdriverPool) {
    this.driverProperties = driverProperties;
    this.webdriverPool = webdriverPool;
  }

  public WebDriver create() {
    //if using remote will setup remote
    if (driverProperties.getWeb().getUseRemote()) {
      return createRemoteWebDriver();
    }

    //if not, will setup locally
    switch (driverProperties.getWeb().getBrowser()) {
      case FIREFOX:
        this.browser = new Firefox();
        break;
      case IE:
        this.browser = new InternetExplorer();
        break;
      case CHROME_HEADLESS:
        this.browser = new Chrome();
        this.browser.setHeadless();
        break;
      case FIREFOX_HEADLESS:
        this.browser = new Firefox();
        this.browser.setHeadless();
        break;
      case SAFARI:
        this.browser = new Safari();
        break;
      case EDGE:
        this.browser = new MicrosoftEdge();
        break;
      default:
        this.browser = new Chrome();
    }
    return setupBrowser();
  }

  private WebDriver createRemoteWebDriver() {
    //setup remote additional capabilities
    DesiredCapabilities remoteCapabilities = new DesiredCapabilities();
    driverProperties.getWeb().getProps().getCapabilities()
        .forEach(remoteCapabilities::setCapability);
    if (driverProperties.getWeb().getCloud().equalsIgnoreCase("selenoid")) {
      //setup selenoid
      remoteCapabilities.setBrowserName(driverProperties.getWeb().getSelenoid().getBrowserName());
      remoteCapabilities.setVersion(driverProperties.getWeb().getSelenoid().getBrowserVersion());
      remoteCapabilities.setCapability("enableVNC",
          driverProperties.getWeb().getSelenoid().getEnableVNC());
      remoteCapabilities.setCapability("enableVideo",
          driverProperties.getWeb().getSelenoid().getEnableVideo());
      remoteCapabilities.setCapability("name", webdriverPool.getScenarioName());
      remoteCapabilities.setCapability("sessionTimeout", "5m");
    } else {
      //setup browserstack
      remoteCapabilities.setCapability("browserstack.selenium_version",
          driverProperties.getWeb().getBrowserstack().getSeleniumVersion());
      remoteCapabilities.setCapability("browser",
          driverProperties.getWeb().getBrowserstack().getBrowserName());
      remoteCapabilities.setCapability("browser_version",
          driverProperties.getWeb().getBrowserstack().getBrowserVersion());
      remoteCapabilities.setCapability("browserstack.local",
          driverProperties.getWeb().getBrowserstack().getLocal());
      remoteCapabilities.setCapability("os", driverProperties.getWeb().getBrowserstack().getOs());
      remoteCapabilities.setCapability("build",
          driverProperties.getWeb().getBrowserstack().getBuild());
      remoteCapabilities.setCapability("name", webdriverPool.getScenarioName());
      remoteCapabilities.setCapability("sessionTimeout", "3m");
      remoteCapabilities.setCapability("console",
          driverProperties.getWeb().getBrowserstack().getConsole());
      remoteCapabilities.setCapability("networkLogs",
          driverProperties.getWeb().getBrowserstack().getNetworkLogs());
      remoteCapabilities.setCapability("debug",
          driverProperties.getWeb().getBrowserstack().getDebug());
    }

    try {
      RemoteWebDriver remoteWebDriver = new RemoteWebDriver(
          URI.create(driverProperties.getWeb().getRemoteUrl()).toURL(), remoteCapabilities);
      setRemoteSessionId(remoteWebDriver.getSessionId());
      if (driverProperties.getWeb().getBrowserSize() != null) {
        remoteWebDriver.manage().window().setSize(
            new Dimension(driverProperties.getWeb().getBrowserSize().get(0),
                driverProperties.getWeb().getBrowserSize().get(1)));
      } else {
        remoteWebDriver.manage().window().setSize(new Dimension(1920, 1080));
      }
      return remoteWebDriver;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("ERROR! -> you must specify valid remote webdriver url!");
      System.exit(2);
      return null;
    }
  }

  private void setRemoteSessionId(SessionId sessionId) {
    this.remoteSessionId = sessionId;
  }

  //should be added more in the future
  private WebDriver setupBrowser() {
    //setup headless
    if (driverProperties.getWeb().getHeadlessMode()) {
      this.browser.setHeadless();
    }

    this.browser.setCapabilities(driverProperties.getWeb().getProps());
    HashMap<String, String> prefs = new HashMap<>();
    prefs.put("download", driverProperties.getWeb().getPathDownload());
    this.browser.setPreference(prefs);
    WebDriver webdriver = this.browser.initialize();
    //setup browser width and height
    if (driverProperties.getWeb().getBrowserSize() != null) {
      if (driverProperties.getWeb().getBrowserSize().size() == 2) {
        webdriver.manage().window().setSize(
            new Dimension(driverProperties.getWeb().getBrowserSize().get(0),
                driverProperties.getWeb().getBrowserSize().get(1)));
      } else {
        webdriver.manage().window().maximize();
      }
    } else {
      webdriver.manage().window().maximize();
    }

    //set implicit wait
    webdriver.manage().timeouts()
        .implicitlyWait(driverProperties.getWeb().getImplicitWait(), TimeUnit.SECONDS);

    if (driverProperties.getWeb().getStartMaximized()) {
      webdriver.manage().window().maximize();
    }

    return webdriver;
  }
}
