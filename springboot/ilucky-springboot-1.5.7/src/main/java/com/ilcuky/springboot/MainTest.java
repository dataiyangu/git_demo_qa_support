package com.ilcuky.springboot;

import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.ilcuky.springboot.util.HttpsClientRequestFactory;

public class MainTest {

    private static RestTemplate restTemplateHttps;
    
    public static void main(String[] args) {
        
        https();
        
    }
    
    
    public static void http() {
//        HttpHeaders headers = HttpEntityFactory.getHttpEntityHeader();
//        String URL = domainConfig.getGatewayUrl()+"/send/"+ SendingTypeEnum.TEMPLATE.value();
//        HttpEntity<String> entity = new HttpEntity<>(JSONObject.toJSONString(templateDTO),headers);
//        ResponseEntity<String> jsonObjectResponseEntity = restTemplate.postForEntity(URL, entity, String.class);
        
        RestTemplate restTemplate=new RestTemplate();
        // IluckySi:分为http和https
        String url="http://localhost:9000/test/java";
        // String url="http://10.0.1.225:8080/test/java";
        // String url="https://10.0.3.42/test/java";
        /* 注意：必须 http、https……开头，不然报错，浏览器地址栏不加 http 之类不出错是因为浏览器自动帮你补全了 */
        HttpHeaders headers = new HttpHeaders();
        /* 这个对象有add()方法，可往请求头存入信息 */       
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        /* 解决中文乱码的关键 , 还有更深层次的问题 关系到 StringHttpMessageConverter，先占位，以后补全*/ 
        String body = "I am IluckySi";
        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        /* body是Http消息体例如json串 */ 
        // ResponseEntity result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        ResponseEntity result = restTemplate.postForEntity(url, entity, String.class);
        /*上面这句返回的是往 url发送 post请求 请求携带信息为entity时返回的结果信息
        String.class 是可以修改的，其实本质上就是在指定反序列化对象类型，这取决于你要怎么解析请求返回的参数*/
        System.out.println(result);
    }
    
    public static void https() {
//        CloseableHttpClient httpClient = HttpClientUtils.acceptsUntrustedCertsHttpClient();
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
////        
//        final String certFile = args[0]; // cert file location
//        final String passwd = args[1]; // mch_id
//        KeyStore keyStore = loadFrom("PKCS12", certFile, passwd);
//        // httpComponent
//        ClientHttpRequestFactory factory = createHttpComponentFactory(keyStore, passwd);
//        RestTemplate restTemplate = new RestTemplate(factory);
        
//        byte[] merchantInfo = "".getBytes();
//        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//        FileInputStream instream = new FileInputStream(new File(merchantInfo.getPfx()));
//        keyStore.load(instream, merchantInfo.getId().toCharArray());
//        // Trust own CA and all self-signed certs
//        SSLContext sslcontext = SSLContextBuilder.create()
//                .loadKeyMaterial(keyStore, merchantInfo.getId().toCharArray())
//                .build();
//        // Allow TLSv1 protocol only
//        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,  new String[]{"TLSv1"},
//                null,hostnameVerifier);
//
//        CloseableHttpClient httpclient = HttpClients.custom()
//                .setSSLSocketFactory(sslsf)
//                .build();
//
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpclient);
//        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        if(restTemplateHttps == null) {
            restTemplateHttps =  new RestTemplate(new HttpsClientRequestFactory());
        }
        
//        try {
//            restTemplateHttps = restTemplate();
//            restTemplateHttps =  new RestTemplate(new HttpsClientRequestFactory());
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
        
        String url= null;
        url = getUrl();
        if(url == null) {
            url = "https://10.0.3.42/test/java";
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String body = "I am IluckySi";
        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity result = restTemplateHttps.postForEntity(url, entity, String.class);
        System.out.println(result);
    }
    
    public static String getUrl() {
        String url = null;
        try {
            // Read config from properties
            Properties props = new Properties();
            InputStream in = MainTest.class.getClassLoader().getResourceAsStream("conf.properties");
            try {
                props.load(in);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("加载conf文件报异常了！");
            }
            System.out.println("======"+props.get("url"));
            if (props.get("url") == null) {
                url = "https://10.0.3.42/test/java";
            } else {
                url = props.get("url").toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
//    
//    private static KeyStore loadFrom(String type, String fileName, String passwd) throws Exception {
//        KeyStore keyStore = KeyStore.getInstance(type);
//        try (FileInputStream fileIn = new FileInputStream(fileName)) {
//            keyStore.load(fileIn, passwd.toCharArray());
//        }
//        System.out.println("keystore entries: " + keyStore.size());
//        return keyStore;
//    }
//
//    private static ClientHttpRequestFactory createOkHttp3Factory(KeyStore keyStore, String passwd) throws Exception {
//        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//        keyManagerFactory.init(keyStore, passwd.toCharArray());
//        SSLContext context = SSLContext.getInstance("TLSV1");
//        context.init(keyManagerFactory.getKeyManagers(), null, null);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .sslSocketFactory(context.getSocketFactory(), getDefaultX509TrustManager())
//            .build();
//        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
//    }
    
//    public static RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//
//        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//                .loadTrustMaterial(null, acceptingTrustStrategy)
//                .build();
//
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
//        HttpComponentsClientHttpRequestFactory requestFactory =new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(httpClient);
//        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        return restTemplate;
//    }
}
