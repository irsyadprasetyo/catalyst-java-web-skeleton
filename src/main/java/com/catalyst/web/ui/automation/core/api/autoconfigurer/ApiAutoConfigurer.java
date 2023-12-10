package com.catalyst.web.ui.automation.core.api.autoconfigurer;

import com.catalyst.web.ui.automation.core.api.properties.ApiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ApiProperties.class})
public class ApiAutoConfigurer {

  public ApiAutoConfigurer() {
  }
}
