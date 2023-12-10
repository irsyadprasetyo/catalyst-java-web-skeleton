package com.catalyst.web.ui.automation.core.ui.properties;


import com.catalyst.web.ui.automation.core.ui.webdriver.enums.BrowserType;
import io.cucumber.spring.ScenarioScope;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
@ScenarioScope
public class WebdriverProperties {

  //start maximize
  private Boolean startMaximized = false;
  //set the browser name
  private BrowserType browser;
  private Integer implicitWait = 10;
  private Integer explicitWait = 10;

  //headless
  private Boolean headlessMode = false;

  //set browser size
  private List<Integer> browserSize;

  //set the remote webdriver
  private Boolean useRemote = false;
  private String cloud;
  private String remoteUrl;

  //set selenoid properties
  private SelenoidProperties selenoid;
  private BrowserstackProperties browserstack;
  private Integer maxDriverInitRetry = 5;

  //set path download
  private String pathDownload;

  private BrowserProperties props;


  /*
   * browser properties data.
   * should be added in the future for configuring more
   * */

  @Data
  public static class BrowserProperties {

    private List<String> arguments;
    private Map<String, String> capabilities = new HashMap<>();
    private Map<String, Integer> numberCapabilities = new HashMap<>();
  }

  /*
   * selenoid properties
   * */
  @Data
  public static class SelenoidProperties {

    private Boolean enableVNC;
    private Boolean enableVideo;
    private String browserName;
    private String browserVersion;
  }

  @Data
  public static class BrowserstackProperties {

    private String seleniumVersion;
    private Boolean local;
    private String os;
    private String build;
    private String name;
    private String browserName;
    private String browserVersion;
    private String console;
    private Boolean networkLogs;
    private Boolean debug;
  }
}
