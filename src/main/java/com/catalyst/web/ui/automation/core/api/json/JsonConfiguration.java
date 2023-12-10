package com.catalyst.web.ui.automation.core.api.json;

import com.fasterxml.jackson.core.JsonParser;

class JsonConfiguration {

  private JsonParser.Feature feature;
  private boolean isEnabled;

  public JsonConfiguration(JsonParser.Feature feature, boolean isEnabled) {
    this.feature = feature;
    this.isEnabled = isEnabled;
  }

  public JsonConfiguration() {
  }

  public static JsonConfigurationBuilder builder() {
    return new JsonConfigurationBuilder();
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof JsonConfiguration)) {
      return false;
    } else {
      JsonConfiguration other = (JsonConfiguration) o;
      if (!other.canEqual(this)) {
        return false;
      } else {
        Object this$feature = this.getFeature();
        Object other$feature = other.getFeature();
        if (this$feature == null) {
          if (other$feature == null) {
            return this.isEnabled() == other.isEnabled();
          }
        } else if (this$feature.equals(other$feature)) {
          return this.isEnabled() == other.isEnabled();
        }

        return false;
      }
    }
  }

  public JsonParser.Feature getFeature() {
    return this.feature;
  }

  public void setFeature(JsonParser.Feature feature) {
    this.feature = feature;
  }

  public int hashCode() {
//        int PRIME = true;
    int result = 1;
    Object $feature = this.getFeature();
    result = result * 59 + ($feature == null ? 43 : $feature.hashCode());
    result = result * 59 + (this.isEnabled() ? 79 : 97);
    return result;
  }

  public boolean isEnabled() {
    return this.isEnabled;
  }

  public void setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public String toString() {
    return "JsonConfiguration(feature=" + this.getFeature() + ", isEnabled=" + this.isEnabled()
        + ")";
  }

  public static class JsonConfigurationBuilder {

    private JsonParser.Feature feature;
    private boolean isEnabled;

    JsonConfigurationBuilder() {
    }

    public JsonConfiguration build() {
      return new JsonConfiguration(this.feature, this.isEnabled);
    }

    public JsonConfigurationBuilder feature(JsonParser.Feature feature) {
      this.feature = feature;
      return this;
    }

    public JsonConfigurationBuilder isEnabled(boolean isEnabled) {
      this.isEnabled = isEnabled;
      return this;
    }

    public String toString() {
      return "JsonConfiguration.JsonConfigurationBuilder(feature=" + this.feature + ", isEnabled="
          + this.isEnabled + ")";
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof JsonConfiguration;
  }
}
