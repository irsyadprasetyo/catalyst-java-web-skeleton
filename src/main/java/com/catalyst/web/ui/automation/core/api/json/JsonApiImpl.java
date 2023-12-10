package com.catalyst.web.ui.automation.core.api.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.catalyst.web.ui.automation.core.api.restassured.ResponseApi;
import io.restassured.response.Response;
import java.util.List;

public class JsonApiImpl implements JsonApi {

  private final ObjectMapper objectMapper;

  public JsonApiImpl(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public <T> T fromJson(String json, Class<T> tClass) {
    try {
      return this.objectMapper.readValue(json, tClass);
    } catch (Exception var6) {
      var6.printStackTrace();

      try {
        return tClass.newInstance();
      } catch (IllegalAccessException | InstantiationException var5) {
        return null;
      }
    }
  }

  public <T> T fromJson(String json, TypeReference typeReference) {
    try {
      return (T) this.objectMapper.readValue(json, typeReference);
    } catch (Exception var4) {
      var4.printStackTrace();
      return null;
    }
  }

  public <T> ResponseApi fromJson(Response response, Class<T> tClass) {
    try {
      T data = this.objectMapper.readValue(response.getBody().asString(), tClass);
      return new ResponseApi(data, response);
    } catch (Exception var4) {
      var4.printStackTrace();
      return this.toResponseApi(response, tClass);
    }
  }

  public <T> ResponseApi fromJson(Response response, TypeReference typeReference) {
    try {
      T data = (T) this.objectMapper.readValue(response.getBody().asString(), typeReference);
      return new ResponseApi(data, response);
    } catch (Exception var4) {
      var4.printStackTrace();
      return new ResponseApi(null, response);
    }
  }

  public <T> T fromJson(String json, Class<T> tClass, List<JsonConfiguration> configs) {
    try {
      ObjectMapper mapper = this.readConfigs(configs);
      return mapper.readValue(json, tClass);
    } catch (Exception var7) {
      var7.printStackTrace();

      try {
        return tClass.newInstance();
      } catch (IllegalAccessException | InstantiationException var6) {
        return null;
      }
    }
  }

  public <T> T fromJson(String json, TypeReference typeReference, List<JsonConfiguration> configs) {
    try {
      ObjectMapper mapper = this.readConfigs(configs);
      return (T) mapper.readValue(json, typeReference);
    } catch (Exception var5) {
      var5.printStackTrace();
      return null;
    }
  }

  public <T> ResponseApi fromJson(Response response, Class<T> tClass,
      List<JsonConfiguration> configs) {
    try {
      ObjectMapper mapper = this.readConfigs(configs);
      T data = mapper.readValue(response.getBody().asString(), tClass);
      return new ResponseApi(data, response);
    } catch (Exception var6) {
      var6.printStackTrace();
      return this.toResponseApi(response, tClass);
    }
  }

  public <T> ResponseApi fromJson(Response response, TypeReference typeReference,
      List<JsonConfiguration> configs) {
    try {
      ObjectMapper mapper = this.readConfigs(configs);
      T data = (T) mapper.readValue(response.getBody().asString(), typeReference);
      return new ResponseApi(data, response);
    } catch (Exception var6) {
      var6.printStackTrace();
      return null;
    }
  }

  public String toJson(Object object) {
    try {
      return this.objectMapper.writeValueAsString(object);
    } catch (Exception var3) {
      var3.printStackTrace();
      return "";
    }
  }

  private ObjectMapper readConfigs(List<JsonConfiguration> configs) {
    ObjectMapper mapper = this.objectMapper;
    configs.forEach((config) -> {
      mapper.configure(config.getFeature(), config.isEnabled());
    });
    return mapper;
  }

  private <T> ResponseApi toResponseApi(Response response, Class<T> tClass) {
    try {
      return new ResponseApi(tClass.newInstance(), response);
    } catch (IllegalAccessException | InstantiationException var4) {
      return new ResponseApi(null, response);
    }
  }
}
