package com.catalyst.web.ui.automation.stepdefinitions.desktop;

import com.catalyst.web.ui.automation.core.ui.pageobject.PageObject;
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

  @Autowired
  PageObject pageObject;

  @Then("user see voila loader disappear")
  public void userSeeVoilaLoaderDisappear() {
    Assert.assertTrue(loginPages.isLoaderVoilaDisappear());
  }

  @Then("user will directed to {string}")
  public void userWillDirectedToVoilaHomepage(String url) {
    pageObject.waitABit(1);
    Assert.assertTrue(pageObject.getDriver().getCurrentUrl().contains(url));
  }
}
