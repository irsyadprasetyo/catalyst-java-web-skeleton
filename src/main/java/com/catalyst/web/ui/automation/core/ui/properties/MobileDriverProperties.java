package com.catalyst.web.ui.automation.core.ui.properties;

import lombok.Data;


@Data
public class MobileDriverProperties {

  private IOSDriverProperties ios;
  private AndroidDriverProperties android;
}
