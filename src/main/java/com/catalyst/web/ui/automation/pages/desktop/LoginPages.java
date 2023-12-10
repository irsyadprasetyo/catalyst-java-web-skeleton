package com.catalyst.web.ui.automation.pages.desktop;

import static com.catalyst.web.ui.automation.locator.desktop.LoginPageLocator.BUTTON_SIGN_IN;
import static com.catalyst.web.ui.automation.locator.desktop.LoginPageLocator.INPUT_EMAIL_OR_PHONE;
import static com.catalyst.web.ui.automation.locator.desktop.LoginPageLocator.INPUT_PASSWORD;
import static com.catalyst.web.ui.automation.locator.desktop.LoginPageLocator.LOADER_SIGN_IN_VOILA;
import com.catalyst.web.ui.automation.core.ui.pageobject.BasePageObject;
import org.springframework.stereotype.Component;

@Component("com.catalyst.web.ui.automation.pages.desktop.LoginPages")
public class LoginPages extends BasePageObject {

  public void inputEmailOrPhone(String emailOrPhone) {
    typeOn(INPUT_EMAIL_OR_PHONE, emailOrPhone);
  }

  public void inputPassword(String password) {
    typeOn(INPUT_PASSWORD, password);
  }

  public void clickButtonSignIn() {
    clickOn(BUTTON_SIGN_IN);
  }

  public Boolean isLoaderVoilaDisappear() {
    return waitUntilInvisible(LOADER_SIGN_IN_VOILA);
  }
}
