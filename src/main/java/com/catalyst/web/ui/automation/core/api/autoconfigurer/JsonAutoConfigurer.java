package com.catalyst.web.ui.automation.core.api.autoconfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.catalyst.web.ui.automation.core.api.json.JsonApi;
import com.catalyst.web.ui.automation.core.api.json.JsonApiImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonAutoConfigurer {

  public JsonAutoConfigurer() {
  }

  @Bean
  public JsonApi json(ObjectMapper objectMapper) {
    return new JsonApiImpl(objectMapper);
  }
}
