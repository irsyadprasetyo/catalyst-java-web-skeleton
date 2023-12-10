package com.catalyst.web.ui.automation.core.api.restassured;

import io.restassured.response.Response;

public class ResponseApi<T> {

  T responseBody;
  Response response;

  public ResponseApi(T responseBody, Response response) {
    this.responseBody = responseBody;
    this.response = response;
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof ResponseApi)) {
      return false;
    } else {
      ResponseApi<?> other = (ResponseApi) o;
      if (!other.canEqual(this)) {
        return false;
      } else {
        Object this$responseBody = this.getResponseBody();
        Object other$responseBody = other.getResponseBody();
        if (this$responseBody == null) {
          if (other$responseBody != null) {
            return false;
          }
        } else if (!this$responseBody.equals(other$responseBody)) {
          return false;
        }

        Object this$response = this.getResponse();
        Object other$response = other.getResponse();
        if (this$response == null) {
          if (other$response != null) {
            return false;
          }
        } else if (!this$response.equals(other$response)) {
          return false;
        }

        return true;
      }
    }
  }

  public Response getResponse() {
    return this.response;
  }

  public void setResponse(Response response) {
    this.response = response;
  }

  public T getResponseBody() {
    return this.responseBody;
  }

  public void setResponseBody(T responseBody) {
    this.responseBody = responseBody;
  }

  public int hashCode() {
//        int PRIME = true;
    int result = 1;
    Object $responseBody = this.getResponseBody();
    result = result * 59 + ($responseBody == null ? 43 : $responseBody.hashCode());
    Object $response = this.getResponse();
    result = result * 59 + ($response == null ? 43 : $response.hashCode());
    return result;
  }

  public String toString() {
    return "ResponseApi(responseBody=" + this.getResponseBody() + ", response=" + this.getResponse()
        + ")";
  }

  protected boolean canEqual(Object other) {
    return other instanceof ResponseApi;
  }
}
