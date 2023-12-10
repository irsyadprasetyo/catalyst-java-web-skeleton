package com.catalyst.web.ui.automation.core.api.restassured;

import com.catalyst.web.ui.automation.core.api.listener.TestLogger;
import com.catalyst.web.ui.automation.core.api.properties.ApiProperties;
import com.catalyst.web.ui.automation.core.api.properties.DefaultRequestProperties;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.FilterableRequestSpecification;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class ServiceApi {

  @Autowired
  protected ApiProperties properties;
  @Autowired
  protected DefaultRequestProperties defRequestProperties;
  @Autowired
  private ApplicationContext applicationContext;

  public ServiceApi() {
  }

  public void checkProperties(String serviceName) {
    ApiProperties.ApiPropertiesDetail detail = (ApiProperties.ApiPropertiesDetail) this.getProperties()
        .getEndpoints().get(serviceName);
    Objects.requireNonNull(detail, () -> {
      return String.format("Configuration is not found for service %s", serviceName);
    });
  }

  public FilterableRequestSpecification service(String serviceName, String account) {
    this.checkProperties(serviceName);
    FilterableRequestSpecification specification = (FilterableRequestSpecification) RestAssured.given();
    if (this.properties.getEnableLogging()) {
      specification.filter(new RequestLoggingFilter(TestLogger.requestCapture))
          .filter(new ResponseLoggingFilter(TestLogger.responseCapture))
          .filter(new ErrorLoggingFilter(TestLogger.errorCapture));
    }

    this.addDefaultBaseUrl(serviceName, specification);
    this.addDefaultRequests(serviceName, specification, account);
    return specification;
  }

  public FilterableRequestSpecification service(String serviceName) {
    return this.service(serviceName, (String) null);
  }

  private void addDefaultBaseUrl(String serviceName, FilterableRequestSpecification specification) {
    String baseUrl = ((ApiProperties.ApiPropertiesDetail) this.getProperties().getEndpoints()
        .get(serviceName)).getBaseUrl();
    if (baseUrl != null && baseUrl.isEmpty()) {
      baseUrl = null;
    }

    Objects.requireNonNull(baseUrl, () -> {
      return String.format("Base Url in service %s must not empty", serviceName);
    });
    specification.baseUri(baseUrl);
    Optional.ofNullable(((ApiProperties.ApiPropertiesDetail) this.getProperties().getEndpoints()
        .get(serviceName)).getPort()).ifPresent(specification::port);
    Optional.ofNullable(((ApiProperties.ApiPropertiesDetail) this.getProperties().getEndpoints()
        .get(serviceName)).getBasePath()).ifPresent(specification::basePath);
  }

  private void addDefaultRequests(String serviceName, FilterableRequestSpecification specification,
      String account) {
    Map<String, DefaultRequests> beans = this.applicationContext.getBeansOfType(
        DefaultRequests.class);
    beans.forEach((beanName, bean) -> {
      if (bean.isUsing(serviceName)) {
        if (account != null && !account.isEmpty()) {
          bean.prepare(specification, account);
        } else {
          bean.prepare(specification);
        }
      }

    });
  }

  private ApiProperties getProperties() {
    return this.properties;
  }
}
