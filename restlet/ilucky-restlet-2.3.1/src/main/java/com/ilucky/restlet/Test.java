package com.ilucky.restlet;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String ip = "";
        String port = "";
        String getChannel = "t3://10.0.3.42:7001";
        String[] str = getChannel.toString().split(":");
        if(str.length == 3) {
            port = str[2];
        } else {
            port = "7001";
        }
        System.out.println(port);
        ip = "0.0.3.42/10.0.3.42";
        if(ip == null || ip.contains("/")) {
            String[] str2 = getChannel.toString().split(":");
            if(str2.length == 3) {
                System.out.println("=========");
                ip = str2[1].substring(2);
                System.out.println(ip);
            } 
           }
        System.out.println(ip);
    }

}
