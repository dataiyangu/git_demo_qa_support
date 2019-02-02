package com.ilucky.ejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientUtility {
   
	private static Context initialContext;
    private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
 
    public static Context getInitialContext() throws NamingException {
        if (initialContext == null) {
            Properties props = new Properties();
            props.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
            
            // props.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT","false");
            // Ilucky
            //props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            // props.put(Context.INITIAL_CONTEXT_FACTORY, " javax.naming.spi.InitialContextFactory");
 //           props.put(Context.PROVIDER_URL, "http-remote://10.0.3.147:4447"); 
//            props.put(Context.SECURITY_PRINCIPAL, "sdx");
//            props.put(Context.SECURITY_CREDENTIALS, "123");
  //          	properties.put("jboss.naming.client.ejb.context", true);  
//	          properties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
////	          properties.put("remote.connections", "default");
//            props.put("remote.connection.default.host", "10.0.3.147");
//            props.put("remote.connection.default.port", "4447"); 
//	          properties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false"); 
	         // properties.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
	          initialContext = new InitialContext(props);
        }
        return initialContext;
    }
}

/**
test
123456
testrole
*/