package com.nettyfour;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleServerHandler
  extends ChannelInboundHandlerAdapter
{
  public void channelRead(ChannelHandlerContext ctx, Object msg)
    throws Exception
  {
    System.out.println("start!!!");
    
    ByteBuf result = (ByteBuf)msg;
    byte[] result1 = new byte[result.readableBytes()];
    
    result.readBytes(result1);
    String resultStr = new String(result1);
    
    System.out.println("Client said:" + resultStr);
    
    result.release();
    
    String response = "hello client!";
    
    ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
    encoded.writeBytes(response.getBytes());
    ctx.write(encoded);
    ctx.flush();
  }
  
  
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
    throws Exception
  {
    cause.printStackTrace();
    ctx.close();
  }
  
  public void channelReadComplete(ChannelHandlerContext ctx)
    throws Exception
  {
    ctx.flush();
  }
}