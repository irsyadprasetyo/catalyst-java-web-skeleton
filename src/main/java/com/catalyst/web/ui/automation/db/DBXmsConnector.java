package com.catalyst.web.ui.automation.db;

import com.catalyst.web.ui.automation.data.DBXmsTableData;
import com.catalyst.web.ui.automation.properties.DatabaseProperties;
import com.catalyst.web.ui.automation.properties.DatabaseProperties.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("com.catalyst.web.ui.automation.db.DBXmsConnector")
public class DBXmsConnector {

  @Autowired
  DBXmsTableData dbData;

  public void accessMsCustomer(Database db, String query, List<String> getValue)
      throws SQLException {
    // Connect db
    Connection connection = DriverManager.getConnection(
        "jdbc:postgresql://" + db.getHost() + ":" + db.getPort() + "/" + db.getDbName(),
        db.getUsername(), db.getPassword());
    Statement st = connection.createStatement();

    // Execute query
    ResultSet result = st.executeQuery(query);

    // Collect data
    Map<String, Object> collectData = new HashMap<>();
    while (result.next()) {
      for (int index = 0; index < getValue.size(); index++) {
        collectData.put(getValue.get(index), result.getString(getValue.get(index)));
      }
    }
    dbData.setMsCustomer(collectData);
    System.out.println("Data: " + collectData);
  }

}
