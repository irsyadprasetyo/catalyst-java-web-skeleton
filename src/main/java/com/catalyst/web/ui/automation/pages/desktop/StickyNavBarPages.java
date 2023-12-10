package com.catalyst.web.ui.automation.pages.desktop;

import static com.catalyst.web.ui.automation.locator.desktop.StickyNavBarLocator.BUTTON_REGISTER;
import static com.catalyst.web.ui.automation.locator.desktop.StickyNavBarLocator.BUTTON_SIGN_IN;
import com.catalyst.web.ui.automation.core.ui.pageobject.BasePageObject;
import org.springframework.stereotype.Component;

@Component("com.catalyst.web.ui.automation.pages.desktop.StickyNavBarPages")
public class StickyNavBarPages extends BasePageObject {

  public void clickSignInOrRegister(String option) {
    if (option.equalsIgnoreCase("sign in")) {
      clickOn(BUTTON_SIGN_IN);
    } else {
      clickOn(BUTTON_REGISTER);
    }
  }

  public Boolean isButtonSignInAppear() {
    try {
      wait(1500);
      return isDisplayed(BUTTON_SIGN_IN);
    } catch (Exception e) {
      return false;
    }
  }
}
