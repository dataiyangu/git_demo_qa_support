package com.nettythree;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class Server
{
  public static void main(String[] args)
  {
    ServerBootstrap bootstrap = new ServerBootstrap();
    
    ExecutorService boss = Executors.newCachedThreadPool();
    ExecutorService worker = Executors.newCachedThreadPool();
    
    bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
    
    bootstrap.setPipelineFactory(new ChannelPipelineFactory()
    {
      public ChannelPipeline getPipeline()
        throws Exception
      {
        ChannelPipeline pipeline = Channels.pipeline();
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        
        pipeline.addLast("helloHandler", new HelloHandler());
        return pipeline;
      }
    });
    bootstrap.bind(new InetSocketAddress(9999));
    InetAddress addr;
	try {
		addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ipnetty3ip+++++++" + ip);
		System.out.println("start!!!");
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}