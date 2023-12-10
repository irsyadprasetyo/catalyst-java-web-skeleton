package com.catalyst.web.ui.automation.properties;

import io.cucumber.spring.ScenarioScope;
import java.util.Map;
import java.util.Objects;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ScenarioScope
@Configuration
@EnableConfigurationProperties({AccountProperties.class})
@ConfigurationProperties(prefix = "account")
public class AccountProperties {

  private Map<String, Account> services;
  private Map<String, Account> data;
  private Account example;

  public Account getAccountService(String key) {
    return services.get(key);
  }

  public Account getData(String key) {
    return data.get(key);
  }

  @Data
  @ScenarioScope
  public static class Account {

    private String username;
    private String password;
    private String utility;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Account account = (Account) o;
      return Objects.equals(username, account.username) && Objects.equals(password,
          account.password) && Objects.equals(utility, account.utility);
    }


    @Override
    public int hashCode() {
      return Objects.hash(username, password, utility);
    }
  }

  @Data
  public static class AccountData {

    private String customerId;
    private String branchId;
  }

}

