package com.easyweb.cert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import org.apache.commons.io.IOUtils;
import javax.crypto.SecretKey;

import sun.misc.BASE64Encoder;

public class Test01 {
	public static void exportCert(KeyStore keyStore, String alias, String exportFile) throws Exception {
		Certificate certificate = keyStore.getCertificate(alias);
		IOUtils.write(certificate.getEncoded(), new FileOutputStream(exportFile));
		/*BASE64Encoder encoder = new BASE64Encoder();
		String encoded = encoder.encode(certificate.getEncoded());
		FileWriter fw = new FileWriter(exportFile);
		fw.write(encoded);
		fw.close();*/
	}

	public static KeyPair getKeyPair(KeyStore keyStore, String alias, char[] password) {
		try {
			Key key = keyStore.getKey(alias, password);
			if (key instanceof PrivateKey) {
				Certificate certificate = keyStore.getCertificate(alias);
				PublicKey publicKey = certificate.getPublicKey();
				return new KeyPair(publicKey, (PrivateKey) key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void exportPrivateKey(PrivateKey privateKey, String exportFile) throws Exception {
		IOUtils.write(privateKey.getEncoded(), new FileOutputStream(exportFile));
		/*BASE64Encoder encoder = new BASE64Encoder();
		String encoded = encoder.encode(privateKey.getEncoded());
		FileWriter fileWriter = new FileWriter(exportFile);
		fileWriter.write(encoded);
		fileWriter.close();*/
	}

	// 导出公钥
	public static void exportPublicKey(PublicKey publicKey, String exportFile) throws Exception {
		IOUtils.write(publicKey.getEncoded(), new FileOutputStream(exportFile));

		/*
		 * BASE64Encoder encoder = new BASE64Encoder(); String encoded =
		 * encoder.encode(publicKey.getEncoded()); FileWriter fileWriter = new
		 * FileWriter(exportFile); fileWriter.write(encoded); fileWriter.close();
		 */
	}

	public static void main(String[] args) throws Exception {

		/*
		 * BASE64Encoder encoder = new BASE64Encoder(); String encoded =
		 * encoder.encode(IOUtils.toByteArray(new
		 * FileInputStream("/Users/xiaojf/tomcat1.txt"))); System.out.println(encoded);
		 */

		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(new FileInputStream("/Users/xiaojf/tomcat.keystore"), "123456".toCharArray());

		Enumeration<String> aliases = keyStore.aliases();
		while (aliases.hasMoreElements())
			System.out.println(aliases.nextElement());

		KeyPair keyPair = getKeyPair(keyStore, "tomcat", "123456".toCharArray());
		exportCert(keyStore, "tomcat", "/Users/xiaojf/tomcat.crt");
		exportPrivateKey(keyPair.getPrivate(), "/Users/xiaojf/tomcat_private.crt");
		exportPublicKey(keyPair.getPublic(), "/Users/xiaojf/tomcat_public.crt");
	}

}
