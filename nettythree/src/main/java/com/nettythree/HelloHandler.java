package com.nettythree;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class HelloHandler
  extends SimpleChannelHandler
{
  public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
    throws Exception
  {
    System.out.println("----->");
    String s = (String)e.getMessage();
    System.out.println("----->" + s);
    
    ctx.getChannel().write("HelloWorld");
    super.messageReceived(ctx, e);
  }
  
  public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
    throws Exception
  {
    System.out.println("exceptionCaught");
    super.exceptionCaught(ctx, e);
  }
  
  public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
    throws Exception
  {
    System.out.println("channelConnected");
    super.channelConnected(ctx, e);
  }
  
  public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e)
    throws Exception
  {
    System.out.println("channelDisconnected");
    super.channelDisconnected(ctx, e);
  }
  
  public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
    throws Exception
  {
    System.out.println("channelClosed");
    super.channelClosed(ctx, e);
  }
  
  }