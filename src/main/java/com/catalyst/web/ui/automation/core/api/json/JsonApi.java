package com.catalyst.web.ui.automation.core.api.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.catalyst.web.ui.automation.core.api.restassured.ResponseApi;
import io.restassured.response.Response;
import java.util.List;

public interface JsonApi {

  <T> T fromJson(String var1, Class<T> var2);

  <T> T fromJson(String var1, TypeReference var2);

  <T> ResponseApi fromJson(Response var1, Class<T> var2);

  <T> ResponseApi fromJson(Response var1, TypeReference var2);

  <T> T fromJson(String var1, Class<T> var2, List<JsonConfiguration> var3);

  <T> T fromJson(String var1, TypeReference var2, List<JsonConfiguration> var3);

  <T> ResponseApi fromJson(Response var1, Class<T> var2, List<JsonConfiguration> var3);

  <T> ResponseApi fromJson(Response var1, TypeReference var2, List<JsonConfiguration> var3);

  String toJson(Object var1);
}
