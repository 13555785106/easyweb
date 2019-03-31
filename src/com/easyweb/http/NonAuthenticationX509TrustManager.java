package com.easyweb.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

class NonAuthenticationX509TrustManager implements X509TrustManager {
	@Override
	public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
		System.out.println("###########################");
		System.out.println(s);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		for(X509Certificate v : x509Certificates)
			System.out.println(v);
	}

	@Override
	public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println(s);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(X509Certificate v : x509Certificates)
			System.out.println(v);
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[0];
	}
}
