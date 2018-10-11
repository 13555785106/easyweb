package com.sample.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test01 {

	public static void main(String[] args) {
		try {
			System.out.println(InetAddress.getByName("www.google.com").isReachable(3000));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
