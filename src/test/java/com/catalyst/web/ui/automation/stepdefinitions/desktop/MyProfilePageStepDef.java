package com.catalyst.web.ui.automation.stepdefinitions.desktop;

import com.catalyst.web.ui.automation.data.DBXmsTableData;
import com.catalyst.web.ui.automation.helper.OtherHelper;
import com.catalyst.web.ui.automation.pages.desktop.MyProfilePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class MyProfilePageStepDef {

  @Autowired
  DBXmsTableData dbXmsData;

  @Autowired
  MyProfilePage profilePage;

  @Autowired
  OtherHelper otherHelper;

  @Then("user verify is on my profile page")
  public void userVerifyIsOnMyProfilePage() {
    Assert.assertThat("user isn't directed profile page", profilePage.isOnMyProfilePage(),
        Matchers.equalTo(Boolean.TRUE));
  }

  @When("user will see my profile data match with ms_customer database:")
  // for accessing this step, you need step userAccessDatabaseMs_customerWithEmailToGetData() first
  public void userWillSeeMyProfileDataMatchWithDatabase(DataTable dataTable) {
    List<Map<String, String>> datas = dataTable.asMaps();
    datas.forEach(dt -> {
      String field = dbXmsData.getMsCustomer().get(dt.get("compare")).toString();
      Assert.assertTrue(String.format("%s not found in this page!", field),
          otherHelper.expectPageHasSomeText(field));
    });
  }
}
