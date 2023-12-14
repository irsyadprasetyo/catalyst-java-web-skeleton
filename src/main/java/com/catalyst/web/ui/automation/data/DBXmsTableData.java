package com.catalyst.web.ui.automation.data;


import io.cucumber.spring.ScenarioScope;
import java.util.Map;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@ScenarioScope
@Component("com.catalyst.web.ui.automation.data.DBXmsTableData")
public class DBXmsTableData {

  public Map<String, Object> msCustomer;

}
