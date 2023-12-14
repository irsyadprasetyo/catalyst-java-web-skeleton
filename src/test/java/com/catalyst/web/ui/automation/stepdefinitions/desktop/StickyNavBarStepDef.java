package com.catalyst.web.ui.automation.stepdefinitions.desktop;

import com.catalyst.web.ui.automation.pages.desktop.StickyNavBarPages;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class StickyNavBarStepDef {

  @Autowired
  StickyNavBarPages stickyNavBarPages;

  @When("user click {string} on dropdown account")
  public void userClickOnDropdownAccount(String option) {
    stickyNavBarPages.clickMyProfile();
  }

  @When("user click {string} button on sticky navbar")
  public void userClickSignInButtonOnStickyNavbar(String button) {
    stickyNavBarPages.clickSignInOrRegister(button);
  }

  @When("user hover profile on sticky navbar")
  public void userHoverProfileOnStickyNavbar() {
    stickyNavBarPages.hoverProfileMenu();
  }

  @When("user verify {string} sign in button on sticky navbar")
  public void userVerifyNotSeeSignInButtonOnStickyNavbar(String expected) {
    if (expected.equalsIgnoreCase("not see")) {
      Assert.assertTrue(stickyNavBarPages.isButtonSignInDisappear());
    } else {
      Assert.assertFalse(stickyNavBarPages.isButtonSignInDisappear());
    }
  }
}
