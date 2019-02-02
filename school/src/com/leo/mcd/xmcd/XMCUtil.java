package com.leo.mcd.xmcd;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class XMCUtil {

	private MemcachedClientBuilder builder = null;
	private MemcachedClient client = null;

	public XMCUtil(String address, int[] weight) {
		builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(address), weight);
		builder.setConnectionPoolSize(5);
		try {
			client = builder.build();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void set(String key, int exp, Object value) {
		try {
			if (!client.set(key, exp, value)) {
				System.err.println("set error, key is " + key + " value is " + value);
			}
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	public void delete(String key) {
		try {
			client.delete(key);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	public void update(String key, final Object value) {
		try {
			client.cas(key, 10, new CASOperation<Object>() {
				public int getMaxTries() {
					return 1;
				}

				public Object getNewValue(long currentCAS, Object currentValue) {
					return value;
				}
			});
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	public Object get(String key) {
		try {
			return client.get(key);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addServer(String server, int port, int weight) {
		try {
			client.addServer(server, port, weight);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeServer(String hostList) {
		client.removeServer(hostList);
	}
}

