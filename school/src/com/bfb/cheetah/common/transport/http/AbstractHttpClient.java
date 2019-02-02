package com.bfb.cheetah.common.transport.http;


import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by AB044973 on 2014/12/15.
 * 
 */
public abstract class AbstractHttpClient implements HttpClient {




    protected String url;

    protected AbstractHttpClient(String url){
        this.url = url;
    }

    protected AbstractHttpClient(){

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static HttpURLConnection getGetConn(String url) throws Exception {
        URL urlNet = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlNet.openConnection();
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("contentType", "utf-8");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("GET");
        return conn;
    }

    public static HttpURLConnection getPostConn(String url) throws Exception {
        URL urlNet = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlNet.openConnection();
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        return conn;
    }
    
    public static HttpURLConnection forPostConn(String url) throws Exception {
        URL urlNet = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlNet.openConnection();
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        return conn;
    }
}
