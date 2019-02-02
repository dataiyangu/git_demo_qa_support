package com.ilucky.ejb.service;

import java.util.Random;

import javax.ejb.Stateless;

@Stateless
public class HelloWorldBean implements HelloWorld {
    public HelloWorldBean() {
    }

    public static void main(String[] args) {
    	HelloWorldBean hw = new HelloWorldBean();
    	try {
			hw.sayHello();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    /**
     * TODO: 是否需要操作数据库
     * @throws Exception 
     */
    public String sayHello() {
    //	try {
    		Random r = new Random();
        	int i = r.nextInt(10);
        	System.out.println("=============EJB Client comming=================(i % 3）=" + (i % 3));
        	if(i % 3 == 0) {
        		int j = 0;
        		//try {
					//throw new Exception("测试");
					System.out.println("========> zero " + i/j);
        	}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//        	
//        	}
//    	} catch (Exception e) {
//    		e.printStackTrace();
//    		System.out.println("-----------------------------END-------------------------------");
//    		System.out.println(e.toString());
//    	}
    	return "Hello World !!!";
    }
}
/**
15:39:26,980 ERROR [stderr] (EJB default - 2) java.lang.ArithmeticException: / by zero
15:39:26,983 ERROR [stderr] (EJB default - 2) 	at com.ilucky.ejb.service.HelloWorldBean.sayHello(HelloWorldBean.java:22)
15:39:26,985 ERROR [stderr] (EJB default - 2) 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
15:39:26,986 ERROR [stderr] (EJB default - 2) 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
15:39:26,987 ERROR [stderr] (EJB default - 2) 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
15:39:26,988 ERROR [stderr] (EJB default - 2) 	at java.lang.reflect.Method.invoke(Method.java:606)
15:39:26,989 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ee.component.ManagedReferenceMethodInterceptorFactory$ManagedReferenceMethodInterceptor.processInvocation(ManagedReferenceMethodInterceptorFactory.java:72)
15:39:26,992 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:26,994 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.WeavedInterceptor.processInvocation(WeavedInterceptor.java:53)
15:39:26,995 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ee.component.interceptors.UserInterceptorFactory$1.processInvocation(UserInterceptorFactory.java:36)
15:39:26,998 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:27,001 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.jpa.interceptor.SBInvocationInterceptor.processInvocation(SBInvocationInterceptor.java:47)
15:39:27,003 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:27,005 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InitialInterceptor.processInvocation(InitialInterceptor.java:21)
15:39:27,006 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:27,007 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.ChainedInterceptor.processInvocation(ChainedInterceptor.java:61)
15:39:27,008 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ee.component.interceptors.ComponentDispatcherInterceptor.processInvocation(ComponentDispatcherInterceptor.java:53)
15:39:27,010 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:27,011 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ejb3.component.pool.PooledInstanceInterceptor.processInvocation(PooledInstanceInterceptor.java:51)
15:39:27,013 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:27,014 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ejb3.tx.CMTTxInterceptor.invokeInOurTx(CMTTxInterceptor.java:228)
15:39:27,016 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ejb3.tx.CMTTxInterceptor.required(CMTTxInterceptor.java:304)
15:39:27,017 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ejb3.tx.CMTTxInterceptor.processInvocation(CMTTxInterceptor.java:190)
15:39:27,018 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:27,019 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ejb3.remote.EJBRemoteTransactionPropogatingInterceptor.processInvocation(EJBRemoteTransactionPropogatingInterceptor.java:80)
15:39:27,020 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:27,021 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ejb3.component.interceptors.CurrentInvocationContextInterceptor.processInvocation(CurrentInvocationContextInterceptor.java:41)
15:39:27,022 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:27,023 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ejb3.component.interceptors.LoggingInterceptor.processInvocation(LoggingInterceptor.java:59)
15:39:27,024 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:27,025 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ee.component.NamespaceContextInterceptor.processInvocation(NamespaceContextInterceptor.java:50)
15:39:27,026 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:27,027 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ee.component.TCCLInterceptor.processInvocation(TCCLInterceptor.java:45)
15:39:27,028 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.InterceptorContext.proceed(InterceptorContext.java:288)
15:39:27,029 ERROR [stderr] (EJB default - 2) 	at org.jboss.invocation.ChainedInterceptor.processInvocation(ChainedInterceptor.java:61)
15:39:27,030 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ee.component.ViewService$View.invoke(ViewService.java:165)
15:39:27,030 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ejb3.remote.protocol.versionone.MethodInvocationMessageHandler.invokeMethod(MethodInvocationMessageHandler.java:300)
15:39:27,032 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ejb3.remote.protocol.versionone.MethodInvocationMessageHandler.access$200(MethodInvocationMessageHandler.java:64)
15:39:27,032 ERROR [stderr] (EJB default - 2) 	at org.jboss.as.ejb3.remote.protocol.versionone.MethodInvocationMessageHandler$1.run(MethodInvocationMessageHandler.java:194)
15:39:27,033 ERROR [stderr] (EJB default - 2) 	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:471)
15:39:27,034 ERROR [stderr] (EJB default - 2) 	at java.util.concurrent.FutureTask.run(FutureTask.java:262)
15:39:27,035 ERROR [stderr] (EJB default - 2) 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
15:39:27,036 ERROR [stderr] (EJB default - 2) 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
15:39:27,037 ERROR [stderr] (EJB default - 2) 	at java.lang.Thread.run(Thread.java:745)
15:39:27,037 ERROR [stderr] (EJB default - 2) 	at org.jboss.threads.JBossThread.run(JBossThread.java:122)
15:39:27,038 INFO  [stdout] (EJB default - 2) -----------------------------END-------------------------------
15:39:27,039 INFO  [stdout] (EJB default - 2) java.lang.ArithmeticException: / by zero
*/