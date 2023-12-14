package com.catalyst.web.ui.automation.pages.desktop;

import static com.catalyst.web.ui.automation.locator.desktop.StickyNavBarLocator.BUTTON_DROPDOWN_MY_PROFILE;
import static com.catalyst.web.ui.automation.locator.desktop.StickyNavBarLocator.BUTTON_REGISTER;
import static com.catalyst.web.ui.automation.locator.desktop.StickyNavBarLocator.BUTTON_SIGN_IN;
import static com.catalyst.web.ui.automation.locator.desktop.StickyNavBarLocator.DROPDOWN_ACCOUNT;
import static com.catalyst.web.ui.automation.locator.desktop.StickyNavBarLocator.LABEL_PROFILE_MENU;
import com.catalyst.web.ui.automation.core.ui.pageobject.BasePageObject;
import org.springframework.stereotype.Component;

@Component("com.catalyst.web.ui.automation.pages.desktop.StickyNavBarPages")
public class StickyNavBarPages extends BasePageObject {

  public void clickMyProfile() {
    clickOn(BUTTON_DROPDOWN_MY_PROFILE);
  }

  public void clickSignInOrRegister(String option) {
    if (option.equalsIgnoreCase("sign in")) {
      clickOn(BUTTON_SIGN_IN);
    } else {
      clickOn(BUTTON_REGISTER);
    }
  }

  public void hoverProfileMenu() {
    hoverTo(LABEL_PROFILE_MENU);
    waitUntilVisible(DROPDOWN_ACCOUNT);
  }

  public Boolean isButtonSignInDisappear() {
    try {
      return waitUntilInvisible(BUTTON_SIGN_IN);
    } catch (Exception er) {
      return Boolean.FALSE;
    }
  }
}
