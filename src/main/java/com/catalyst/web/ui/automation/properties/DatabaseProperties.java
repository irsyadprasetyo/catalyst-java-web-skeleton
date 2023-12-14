package com.catalyst.web.ui.automation.properties;

import io.cucumber.spring.ScenarioScope;
import java.util.Map;
import java.util.Objects;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Data
@ScenarioScope
@Configuration
@EnableConfigurationProperties({DatabaseProperties.class})
@ConfigurationProperties(prefix = "database")
@Primary
public class DatabaseProperties {

  private Map<String, Database> database;
  private Map<String, Database> data;

  public Database getData(String key) {return data.get(key);}

  public Database getDatabase(String key) {return database.get(key);}

  @Data
  @ScenarioScope
  public static class Database {

    private String host;
    private String port;
    private String dbName;
    private String username;
    private String password;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Database database = (Database) o;
      return Objects.equals(host, database.host) && Objects.equals(port, database.port)
          && Objects.equals(dbName, database.dbName) && Objects.equals(username, database.username)
          && Objects.equals(password, database.password);
    }

    @Override
    public int hashCode() {
      return Objects.hash(host, port, dbName, username, password);
    }


  }

}
