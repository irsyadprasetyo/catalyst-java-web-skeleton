package com.catalyst.web.ui.automation.locator.desktop;

import org.openqa.selenium.By;

public interface LoginPageLocator {

  By INPUT_EMAIL_OR_PHONE = By.name("identifier");
  By INPUT_PASSWORD = By.name("password");
  By BUTTON_SIGN_IN = By.xpath("//button[@data-test-id='CT_component_login_submit']");
  By LOADER_SIGN_IN_VOILA = By.xpath("//*[@id='base']/div/canvas");

}
