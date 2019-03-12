package com.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class MinaServer {
	static int PORT = 7080;
	static IoAcceptor accept = null; // 提供服务端实现

	public static void main(String[] args) {
		try {
			accept = new NioSocketAcceptor();
			// 设置过滤器
			accept.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new TextLineCodecFactory(Charset
							.forName("UTF-8"),
							LineDelimiter.WINDOWS.getValue(),
							LineDelimiter.WINDOWS.getValue())));

			// 设置缓冲区
			accept.getSessionConfig().setReadBufferSize(1024);// 大小
			accept.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);// 时间10s
			accept.setHandler(new MyHandler());// 业务
			accept.bind(new InetSocketAddress(PORT));// 绑定端口并启动
			System.out.println("Server ->" + PORT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

