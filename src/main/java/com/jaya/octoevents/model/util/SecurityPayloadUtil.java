package com.jaya.octoevents.model.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SecurityPayloadUtil {

	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	private static String toHexString(byte[] bytes) {
		try (Formatter formatter = new Formatter()) {
			for (byte b : bytes) {
				formatter.format("%02x", b);
			}

			return formatter.toString();
		}
	}

	public static String calculateHMACSHA1(String data, String key)
		throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
	{
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
		Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
		mac.init(signingKey);
		return "sha1="+toHexString(mac.doFinal(data.getBytes()));
	}
	
}
