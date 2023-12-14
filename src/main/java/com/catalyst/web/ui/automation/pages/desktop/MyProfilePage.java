package com.catalyst.web.ui.automation.pages.desktop;

import static com.catalyst.web.ui.automation.locator.desktop.MyProfilePageLocator.CONTAINER_SIGN_OUT;
import com.catalyst.web.ui.automation.core.ui.pageobject.BasePageObject;
import org.springframework.stereotype.Component;

@Component("com.catalyst.web.ui.automation.pages.desktop.BasePageObject")
public class MyProfilePage extends BasePageObject {

  public Boolean isOnMyProfilePage() {
    return waitUntilPresent(CONTAINER_SIGN_OUT).isDisplayed();
  }

}
