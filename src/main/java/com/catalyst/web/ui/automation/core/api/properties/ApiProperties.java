package com.catalyst.web.ui.automation.core.api.properties;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service")
public class ApiProperties {

  private Boolean enableLogging;
  private Map<String, ApiPropertiesDetail> endpoints;

  public ApiProperties() {
    this.enableLogging = Boolean.TRUE;
    this.endpoints = new HashMap();
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof ApiProperties)) {
      return false;
    } else {
      ApiProperties other = (ApiProperties) o;
      if (!other.canEqual(this)) {
        return false;
      } else {
        Object this$enableLogging = this.getEnableLogging();
        Object other$enableLogging = other.getEnableLogging();
        if (this$enableLogging == null) {
          if (other$enableLogging != null) {
            return false;
          }
        } else if (!this$enableLogging.equals(other$enableLogging)) {
          return false;
        }

        Object this$endpoints = this.getEndpoints();
        Object other$endpoints = other.getEndpoints();
        if (this$endpoints == null) {
          return other$endpoints == null;
        } else {
          return this$endpoints.equals(other$endpoints);
        }
      }
    }
  }

  public Boolean getEnableLogging() {
    return this.enableLogging;
  }

  public void setEnableLogging(Boolean enableLogging) {
    this.enableLogging = enableLogging;
  }

  public Map<String, ApiPropertiesDetail> getEndpoints() {
    return this.endpoints;
  }

  public void setEndpoints(Map<String, ApiPropertiesDetail> endpoints) {
    this.endpoints = endpoints;
  }

  public int hashCode() {
//        int PRIME = true;
    int result = 1;
    Object $enableLogging = this.getEnableLogging();
    result = result * 59 + ($enableLogging == null ? 43 : $enableLogging.hashCode());
    Object $endpoints = this.getEndpoints();
    result = result * 59 + ($endpoints == null ? 43 : $endpoints.hashCode());
    return result;
  }

  public String toString() {
    return "ApiProperties(enableLogging=" + this.getEnableLogging() + ", endpoints="
        + this.getEndpoints() + ")";
  }

  public static class ApiPropertiesDetail {

    private String baseUrl;
    private Integer port = null;
    private String basePath = "";

    public ApiPropertiesDetail() {
    }

    public boolean equals(Object o) {
      if (o == this) {
        return true;
      } else if (!(o instanceof ApiPropertiesDetail)) {
        return false;
      } else {
        ApiPropertiesDetail other = (ApiPropertiesDetail) o;
        if (!other.canEqual(this)) {
          return false;
        } else {
          label47:
          {
            Object this$baseUrl = this.getBaseUrl();
            Object other$baseUrl = other.getBaseUrl();
            if (this$baseUrl == null) {
              if (other$baseUrl == null) {
                break label47;
              }
            } else if (this$baseUrl.equals(other$baseUrl)) {
              break label47;
            }

            return false;
          }

          Object this$port = this.getPort();
          Object other$port = other.getPort();
          if (this$port == null) {
            if (other$port != null) {
              return false;
            }
          } else if (!this$port.equals(other$port)) {
            return false;
          }

          Object this$basePath = this.getBasePath();
          Object other$basePath = other.getBasePath();
          if (this$basePath == null) {
            return other$basePath == null;
          } else {
            return this$basePath.equals(other$basePath);
          }
        }
      }
    }

    public String getBasePath() {
      return this.basePath;
    }

    public void setBasePath(String basePath) {
      this.basePath = basePath;
    }

    public String getBaseUrl() {
      return this.baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
    }

    public Integer getPort() {
      return this.port;
    }

    public void setPort(Integer port) {
      this.port = port;
    }

    public int hashCode() {
//            int PRIME = true;
      int result = 1;
      Object $baseUrl = this.getBaseUrl();
      result = result * 59 + ($baseUrl == null ? 43 : $baseUrl.hashCode());
      Object $port = this.getPort();
      result = result * 59 + ($port == null ? 43 : $port.hashCode());
      Object $basePath = this.getBasePath();
      result = result * 59 + ($basePath == null ? 43 : $basePath.hashCode());
      return result;
    }

    public String toString() {
      return "ApiProperties.ApiPropertiesDetail(baseUrl=" + this.getBaseUrl() + ", port="
          + this.getPort() + ", basePath=" + this.getBasePath() + ")";
    }

    protected boolean canEqual(Object other) {
      return other instanceof ApiPropertiesDetail;
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof ApiProperties;
  }
}
