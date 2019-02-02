package com.cloudwise.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * Hello world!
 *
 */
public class App  extends AbstractVerticle
{
    public static void main( String[] args )
    {
    	System.out.println("a");
    	 Vertx.vertx().createHttpServer().requestHandler(req -> req.response().
    		        end("Hello World!")).listen(8082);
    }
}
