//package com.ilucky.ejb;
//
//import java.util.Properties;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//public class TestRemoteClient {
//    public static void main(String[] args) {
//        if (args.length < 2)
//        {
//            System.err.println ("2 integer values expected");
//            System.exit(99);
//         }
//        int val1 = parseInt(args [0]);
//        int val2 = parseInt(args [1]);
//       
//        Properties props = new Properties();
// 
//        final String bean = "TestRemoteJNDI/TestRemoteJNDI-ejb/CalculateBean!ejb.CalculateBeanRemote";
// 
//        CalculateBeanRemote cal;
//        System.out.println("Testing with: " + bean);
//             
//        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//        props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
//        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//        props.put(Context.SECURITY_PRINCIPAL, "guest");
//        props.put(Context.SECURITY_CREDENTIALS, "guest");
// 
//        // to avoid: java.lang.IllegalStateException: EJBCLIENT000025: No EJB receiver available
//        props.put("jboss.naming.client.ejb.context", true);
//        try {
//            final Context context = new InitialContext(props);
//            cal = (CalculateBeanRemote) context
//                    .lookup(bean);
//            System.out.println("Lookup complete");
// 
//            System.out.println(val1 + " + " + val2 + " = " + cal.add(val1, val2));
//            // context.close();   // throws: java.util.concurrent.RejectedExecutionException
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }
//}