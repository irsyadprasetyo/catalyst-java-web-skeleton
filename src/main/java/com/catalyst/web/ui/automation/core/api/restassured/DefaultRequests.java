package com.catalyst.web.ui.automation.core.api.restassured;

import io.restassured.specification.RequestSpecification;

public interface DefaultRequests {

  default boolean isUsing(String endpointName) {
    return true;
  }

  void prepare(RequestSpecification var1);

  void prepare(RequestSpecification var1, String var2);
}
