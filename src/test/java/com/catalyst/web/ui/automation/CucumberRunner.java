package com.catalyst.web.ui.automation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(publish = true, stepNotifications = true, glue = {"com.catalyst.web.ui.automation"},
    plugin = {"json:build/cucumber.json", "html:build/result.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    features = "src/test/resources/features", tags = "@sample.feature")
public class CucumberRunner {

}
