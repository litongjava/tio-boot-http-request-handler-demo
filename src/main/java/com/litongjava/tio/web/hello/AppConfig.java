package com.litongjava.tio.web.hello;

import com.litongjava.tio.boot.context.TioBootConfiguration;

public class AppConfig implements TioBootConfiguration {

  @Override
  public void config() {
    new HttpRequestHanlderConfig().config();
  }
}
