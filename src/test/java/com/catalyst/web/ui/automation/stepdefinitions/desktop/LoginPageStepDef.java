package com.catalyst.web.ui.automation.stepdefinitions.desktop;

import com.catalyst.web.ui.automation.pages.desktop.LoginPages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginPageStepDef {

  @Autowired
  LoginPages loginPages;

  @When("user click sign in button on login page")
  public void userClickSignInButtonOnLoginPage() {
    loginPages.clickButtonSignIn();
  }

  @And("user fill password {string} on login page")
  public void userFillPasswordOnLoginPage(String password) {
    loginPages.inputPassword(password);
  }

  @And("user fill username {string} on login page")
  public void userFillUsernameOnLoginPage(String email) {
    loginPages.inputEmailOrPhone(email);
  }
}
