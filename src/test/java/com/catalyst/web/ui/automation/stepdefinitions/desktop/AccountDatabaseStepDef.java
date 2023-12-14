package com.catalyst.web.ui.automation.stepdefinitions.desktop;

import com.catalyst.web.ui.automation.data.DBXmsTableData;
import com.catalyst.web.ui.automation.db.DBXmsConnector;
import com.catalyst.web.ui.automation.helper.OtherHelper;
import com.catalyst.web.ui.automation.properties.DatabaseProperties;
import com.catalyst.web.ui.automation.properties.DatabaseProperties.Database;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountDatabaseStepDef {

  @Autowired
  DatabaseProperties dbProps;

  @Autowired
  DBXmsConnector dbXms;

  @Autowired
  DBXmsTableData dbXmsData;

  @Autowired
  OtherHelper otherHelper;

  @When("user access database xms ms_customer with email {string} to get data")
  public void userAccessDatabaseXmsMs_customerWithEmailToGetData(String email, DataTable dataTable)
      throws SQLException {
    List<String> collectListOfData = new ArrayList<>();
    List<Map<String, String>> datas = dataTable.asMaps();
    datas.forEach(dt -> {
      collectListOfData.add(dt.get("listOfData"));
    });

    String data = otherHelper.joinListOfArrays(collectListOfData, ",", "\"");
    String query = "SELECT " + data + " FROM public.ms_customer mc WHERE email = '" + email + "'";

    Database database = dbProps.getData("xms");
    dbXms.accessMsCustomer(database, query, collectListOfData);
  }
}
