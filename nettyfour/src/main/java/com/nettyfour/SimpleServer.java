package com.nettyfour;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.PrintStream;
import java.net.InetAddress;

public class SimpleServer
{
  private int port;
  private String ip;
  
  public SimpleServer(String ip, int port)
  {
    this.port = port;
    this.ip = ip;
  }
  
  public void run()
    throws Exception
  {
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try
    {
      ServerBootstrap b = new ServerBootstrap();
      
      ((ServerBootstrap)((ServerBootstrap)b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)).childHandler(new ChannelInitializer()
      {
        public void initChannel(SocketChannel ch)
          throws Exception
        {
          ch.pipeline().addLast(new ChannelHandler[] { new SimpleServerHandler() });
        }

		@Override
		protected void initChannel(Channel ch) throws Exception {
			// TODO Auto-generated method stub
			
		}
      })
      
        .option(ChannelOption.SO_BACKLOG, Integer.valueOf(128)))
        .childOption(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true));
      
      ChannelFuture f = b.bind(this.ip, this.port).sync();
      
      f.channel().closeFuture().sync();
    }
    finally
    {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }
  
  public static void main(String[] args)
    throws Exception
  {
    InetAddress addr = InetAddress.getLocalHost();
    String ip = addr.getHostAddress().toString();
    System.out.println("ipnetty4ip+++++++" + ip);
    new SimpleServer(ip, 9999).run();
  }
}