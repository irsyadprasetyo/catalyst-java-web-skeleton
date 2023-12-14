package com.catalyst.web.ui.automation.helper;

import com.catalyst.web.ui.automation.core.ui.pageobject.PageObject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("com.catalyst.web.ui.automation.helper.OtherHelper")
public class OtherHelper {

  @Autowired
  PageObject pageObject;

  /**
   * function for existing text on current page
   *
   * @param text expected text inside of current page
   * @return true if list not empty
   */
  public boolean expectPageHasSomeText(String text) {
    List<WebElement> list = Collections.singletonList(
        pageObject.find(By.xpath("//*[contains(text(),'" + text + "')]")));
    return !list.isEmpty();
  }

  /**
   * function for join array string to be 1 line with separator & enclose but, you can avoid
   * separator and enclose with ""
   *
   * @param listOfArrays set your array string -> e.g ({one, two, three})
   * @param separator    set separator -> e.g "," / "." / "-"
   * @param enclose      set your enclose of each index -> e.g 'one-quotes', "two-quotes"
   * @return -> "one", "two", "three" (based on your separator)
   */
  public String joinListOfArrays(List<String> listOfArrays, String separator, String enclose) {
    String result = listOfArrays.stream().map(element -> enclose + element + enclose)
        .collect(Collectors.joining(separator));

    return result;
  }

}
