package com.catalyst.web.ui.automation.stepdefinitions.desktop;

import com.catalyst.web.ui.automation.pages.desktop.LoginPages;
import com.catalyst.web.ui.automation.pages.desktop.StickyNavBarPages;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class StickyNavBarStepDef {

  @Autowired
  StickyNavBarPages stickyNavBarPages;

  @And("user click {string} button on sticky navbar")
  public void userClickSignInButtonOnStickyNavbar(String button) {
    stickyNavBarPages.clickSignInOrRegister(button);
  }

  @And("user verify {string} sign in button on sticky navbar")
  public void userVerifyNotSeeSignInButtonOnStickyNavbar(String expected) {
    if (expected.equalsIgnoreCase("see")) {
      Assert.assertTrue(stickyNavBarPages.isButtonSignInAppear());
    } else {
      Assert.assertFalse(stickyNavBarPages.isButtonSignInAppear());
    }
  }
}
