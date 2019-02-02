package com.cloudwise.kafuka;

import java.util.Arrays;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		String a ="{auto.offset.reset=smallest, auto.commit.interval.ms=1000, group.id=jd-group, serializer.class=kafka.serializer.StringEncoder, zookeeper.session.timeout.ms=4000, zookeeper.connect=10.0.1.48:2181, zookeeper.sync.time.ms=200} .";
		String b = "10.0.1.48:2181" ;
		String []c =b.split(":");
		System.out.println("c="+c[0]);

		//       String[] arrays =a.split(",");
//		String[] split = a.split(",");
//		System.out.println("b="+arrays);

		//		List<String> strsToList1= Arrays.asList(a);
//		System.out.println("a="+strsToList1);
//		String c =strsToList1.get(0);
//
//		for(String s:strsToList1){
//			System.out.println("b="+s);
//
//		}
	}
}
