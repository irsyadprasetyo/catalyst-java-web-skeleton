package com.catalyst.web.ui.automation.stepdefinitions.desktop;

import com.catalyst.web.ui.automation.pages.desktop.HomePages;
import com.catalyst.web.ui.automation.pages.desktop.LoginPages;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class HomepageStepDef {

  @Autowired
  HomePages homePages;

  @Autowired
  LoginPages loginPages;

  @Then("user will directed to voila homepage with {string}")
  public void userWillDirectedToVoilaHomepage(String url) {
    Assert.assertTrue(loginPages.isLoaderVoilaDisappear());
  }
}
