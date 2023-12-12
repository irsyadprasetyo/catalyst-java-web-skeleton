package com.catalyst.web.ui.automation.stepdefinitions.desktop;

import com.catalyst.web.ui.automation.pages.VisitPages;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class VisitStepDef {

  @Autowired
  VisitPages visitPages;

  @Given("desktop/mweb user visit voila homepage")
  public void userVisitVoilaHomepage() {
    visitPages.visitVoilaHomepage();
  }
}
