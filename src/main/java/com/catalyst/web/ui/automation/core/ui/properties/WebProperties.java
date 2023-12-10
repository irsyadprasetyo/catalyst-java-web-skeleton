package com.catalyst.web.ui.automation.core.ui.properties;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "web", ignoreUnknownFields = false)
public class WebProperties {

  private Map<String, WebPropertiesDetail> endpoints;


  public String getEndpoints(String name) {
    return endpoints.get(name).getBase();
  }

  public String getPath(String name, String pathName) {
    return endpoints.get(name).getPath(pathName);
  }


  @Data
  public static class WebPropertiesDetail {

    private String base;
    private Map<String, String> paths;

    String getPath(String name) {
      return paths.get(name);
    }
  }
}
