package com.catalyst.web.ui.automation.core.ui.autoconfigurer;

import com.catalyst.web.ui.automation.core.ui.properties.WebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({WebProperties.class})
public class WebAutoConfigurer {

}
