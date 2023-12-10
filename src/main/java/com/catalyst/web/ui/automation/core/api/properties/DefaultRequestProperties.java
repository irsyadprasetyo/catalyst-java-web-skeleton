package com.catalyst.web.ui.automation.core.api.properties;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "defrequest")
public class DefaultRequestProperties {

  private static final String KEY_SERVICE = "service";
  private static final String KEY_GATEWAY = "endpoints";
  private Map<String, String> name;

  public DefaultRequestProperties() {
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof DefaultRequestProperties)) {
      return false;
    } else {
      DefaultRequestProperties other = (DefaultRequestProperties) o;
      if (!other.canEqual(this)) {
        return false;
      } else {
        Object this$name = this.getName();
        Object other$name = other.getName();
        if (this$name == null) {
          if (other$name != null) {
            return false;
          }
        } else if (!this$name.equals(other$name)) {
          return false;
        }

        return true;
      }
    }
  }

  public String get(String key) {
    return (String) this.name.get(key);
  }

  public String getKeyGateway() {
    return (String) this.name.get("gateway");
  }

  public String getKeyService() {
    return (String) this.name.get("service");
  }

  public Map<String, String> getName() {
    return this.name;
  }

  public void setName(Map<String, String> name) {
    this.name = name;
  }

  public int hashCode() {
//        int PRIME = true;
    int result = 1;
    Object $name = this.getName();
    result = result * 59 + ($name == null ? 43 : $name.hashCode());
    return result;
  }

  public String toString() {
    return "DefaultRequestProperties(name=" + this.getName() + ")";
  }

  protected boolean canEqual(Object other) {
    return other instanceof DefaultRequestProperties;
  }
}
