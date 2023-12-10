package com.catalyst.web.ui.automation;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = WebAutomation.class)
class UiAutomationApplicationTests {

  @Test
  void contextLoads() {
  }

}
