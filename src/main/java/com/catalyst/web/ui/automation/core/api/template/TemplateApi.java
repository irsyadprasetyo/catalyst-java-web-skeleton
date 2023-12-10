package com.catalyst.web.ui.automation.core.api.template;

import java.util.Collections;

public interface TemplateApi {

  String create(String var1, Object var2);

  default String create(String templateName) {
    return this.create(templateName, Collections.emptyMap());
  }

  String createFromString(String var1, Object var2);
}
