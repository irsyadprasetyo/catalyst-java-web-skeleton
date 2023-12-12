package com.catalyst.web.ui.automation.pages;

import static com.catalyst.web.ui.automation.constant.WebUrlConstant.URL_VOILA_HOMEPAGE;
import com.catalyst.web.ui.automation.core.ui.pageobject.BasePageObject;
import com.catalyst.web.ui.automation.core.ui.properties.WebProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("com.catalyst.web.ui.automation.pages.VisitPages")
public class VisitPages extends BasePageObject {

  @Autowired
  WebProperties webProperties;

  public void visitVoilaHomepage() {
    openAt(webProperties.getEndpoints(URL_VOILA_HOMEPAGE));
    waitABit(10);
  }
}
