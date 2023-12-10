package com.catalyst.web.ui.automation.core.ui.autoconfigurer;

import com.catalyst.web.ui.automation.core.ui.properties.DriverProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({DriverProperties.class})
public class WebdriverAutoConfigurer {

}
