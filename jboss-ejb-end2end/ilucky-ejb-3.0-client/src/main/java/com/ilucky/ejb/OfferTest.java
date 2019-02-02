package com.ilucky.ejb;

import java.util.concurrent.ArrayBlockingQueue;

public class OfferTest {

	public static void main(String[] args) {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
		System.out.println(queue.offer("s"));
		System.out.println(queue.offer("d"));
		System.out.println(queue.offer("x"));
		System.out.println(queue.toString());
	}
}
/**
true
true
true
[s, d, x]
*/