package com.bmw.boss.infos.app.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CMSApiHelper {

	public static String generateHASHKey(String rawString, String key) throws NoSuchAlgorithmException, InvalidKeyException {

		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signingKey);
		byte[] resultArray = mac.doFinal(rawString.getBytes());

		String resultString = Base64.encodeBase64String(resultArray);
		resultString = resultString.replaceAll("[+]", "-");
		resultString = resultString.replaceAll("[/]", "_");
		return resultString;

	}

}
