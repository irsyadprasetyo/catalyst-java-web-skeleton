package com.catalyst.web.ui.automation.stepdefinitions.desktop;

import com.catalyst.web.ui.automation.db.DBXmsConnector;
import com.catalyst.web.ui.automation.pages.VisitPages;
import com.catalyst.web.ui.automation.properties.AccountProperties.Account;
import com.catalyst.web.ui.automation.properties.DatabaseProperties;
import com.catalyst.web.ui.automation.properties.DatabaseProperties.Database;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.crypto.Data;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType.SignatureRelevant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class VisitStepDef {

  @Autowired
  VisitPages visitPages;

  @Given("desktop/mweb user visit voila homepage")
  public void userVisitVoilaHomepage() {
    visitPages.visitVoilaHomepage();
  }
}
