package com.cloudwise.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.impl.launcher.commands.StartCommand;
public class EchoServer extends AbstractVerticle {
//	public static void main(String[] args) {
//	}
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
            .putHeader("content-type", "text/plain")
            .end("Hello from Vert.x!");
        }).listen(8081);
    }
}
