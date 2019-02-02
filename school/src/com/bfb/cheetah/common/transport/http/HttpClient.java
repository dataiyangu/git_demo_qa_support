package com.bfb.cheetah.common.transport.http;

import java.util.List;
import java.util.Map;

/**
 * Created by AB044973 on 2014/12/17.
 */
public interface HttpClient {
    public abstract String execute(String url) throws Exception;

    public abstract String execute(List<Map.Entry<String,String>>content) throws Exception;

    public abstract String execute(String url, List<Map.Entry<String,String>> content) throws Exception;

    public String execute(String url, String content, String formatType) throws Exception;

    public String execute(String url, String content, String formatType, int timeOut) throws Exception;
}
