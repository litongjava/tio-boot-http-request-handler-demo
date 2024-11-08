package com.litongjava.tio.web.hello.config;

import com.litongjava.context.BootConfiguration;

public class AppConfig implements BootConfiguration {

  @Override
  public void config() {
    new HttpRequestHanlderConfig().config();
  }
}
