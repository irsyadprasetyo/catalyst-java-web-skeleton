package com.catalyst.web.ui.automation.locator.desktop;

import org.openqa.selenium.By;

public interface StickyNavBarLocator {

  By BUTTON_SIGN_IN = By.xpath("//button[@data-test-id='CT-SignIn-Btn']");
  By BUTTON_REGISTER = By.xpath("//button[@data-test-id='CT-Register-Btn']");
}
