package com.litongjava.tio.web.hello.config;

import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpRequestRouter;
import com.litongjava.tio.web.hello.handler.HelloRequestHandler;

public class HttpRequestHanlderConfig {

  public void config() {

     HttpRequestRouter r = TioBootServer.me().getRequestRouter();

    // handler
    HelloRequestHandler HelloRequestHandler = new HelloRequestHandler();

    // 添加handler
    r.add("/hi", HelloRequestHandler::hi);
    r.add("/hello", HelloRequestHandler::hello);

  }
}
