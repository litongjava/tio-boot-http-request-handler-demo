package com.litongjava.tio.web.hello;

import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpReqeustSimpleHandlerRoute;

public class HttpRequestHanlderConfig {

  public void config() {

    HttpReqeustSimpleHandlerRoute r = TioBootServer.me().getHttpReqeustSimpleHandlerRoute();

    // 创建controller
    HelloRequestHandler HelloRequestHandler = new HelloRequestHandler();

    // 添加action
    r.add("/hi", HelloRequestHandler::hi);
    r.add("/hello", HelloRequestHandler::hello);

  }
}
