package com.catalyst.web.ui.automation.locator.desktop;

import org.openqa.selenium.By;

public interface StickyNavBarLocator {

  By BUTTON_SIGN_IN = By.xpath("//button[@data-test-id='CT-SignIn-Btn']");
  By BUTTON_REGISTER = By.xpath("//button[@data-test-id='CT-Register-Btn']");
  By LABEL_PROFILE_MENU = By.xpath("//a[@data-test-id='CT_Component_profileMenu']");
  By DROPDOWN_ACCOUNT = By.xpath("//div[@data-test-id='CT_Dropdown_Account']");

  // Dropdown account
  By BUTTON_DROPDOWN_MY_PROFILE = By.xpath("//div[@data-test-id='CT_account_navigation-item_My Profile']");
  By BUTTON_DROPDOWN_SIGN_OUT = By.xpath("//div[@data-test-id='CT_account_navigation-item_Sign Out']");
}
