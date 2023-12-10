package com.catalyst.web.ui.automation.core.api.autoconfigurer;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.module.jsv.JsonSchemaValidatorSettings;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonSchemaAutoConfigurer implements InitializingBean {

  public JsonSchemaAutoConfigurer() {
  }

  public void afterPropertiesSet() throws Exception {
    JsonSchemaValidator.settings = JsonSchemaValidatorSettings.settings().with().jsonSchemaFactory(
        JsonSchemaFactory.newBuilder().setValidationConfiguration(
                ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
            .freeze()).and().with().checkedValidation(false);
  }
}
