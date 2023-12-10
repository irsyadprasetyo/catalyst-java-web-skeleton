package com.catalyst.web.ui.automation.core.api.autoconfigurer;

import com.catalyst.web.ui.automation.core.api.template.TemplateApi;
import com.catalyst.web.ui.automation.core.api.template.TemplateApiImpl;
import com.samskivert.mustache.Mustache.Compiler;
import com.samskivert.mustache.Mustache.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemplateAutoConfigurer {

  public TemplateAutoConfigurer() {
  }

  @Bean
  @Autowired
  public TemplateApi templateApi(TemplateLoader templateLoader, Compiler compiler) {
    return new TemplateApiImpl(templateLoader, compiler);
  }
}
