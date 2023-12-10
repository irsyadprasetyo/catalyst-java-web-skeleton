package com.catalyst.web.ui.automation.core.ui.properties;

import io.cucumber.spring.ScenarioScope;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@ScenarioScope
@Component("com.catalyst.web.ui.automation.core.ui.properties.AndroidDriverProperties")
public class AndroidDriverProperties {

  private String appiumUrl;
  private Boolean useAppiumDesktop = true;
  private Map<String, String> capabilities = new HashMap<>();
  private Map<String, Integer> numberCapabilities = new HashMap<>();
  private List<String> browserNameList;
  private Integer implicitWait = 5;
  private Integer explicitWait = 5;
  private Integer maxDriverInitRetry = 5;

}
