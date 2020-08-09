package testutils;

import org.apache.commons.codec.binary.Base64;

public class EncrpytString {

	public static void passwordEncoder(String key) {
		String encodedPWD = "";
		try {
			byte[] encodedPwdBytes = Base64.encodeBase64(key.getBytes());
			encodedPWD = new String(encodedPwdBytes);
			System.out.println("Encoded Password :" + encodedPWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String passwordDecoder(String key) {
		String decodedPWD = "";
		try {
			byte[] decodePwdBytes = Base64.decodeBase64(key);
			decodedPWD = new String(decodePwdBytes);
			System.out.println("Decoded Password :" + decodedPWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (decodedPWD);
	}

	public static void main(String args[]) {
		EncrpytString.passwordDecoder("UFJBVkVFTg==");
	}
}

/*********************************************************************************************
 * If we use Base 64 of the Java.util.Base64 then we can use this below to
 * encode and decode byte[] encryptedPasswordBytes =
 * Base64.getEncoder().encode(encryptedPassword); byte[] decryptedPasswordBytes
 * = Base64.getDecoder().decode(encryptedPassword); public static void
 * main(String [] args) throws IOException { PwdEncryption pwd = new
 * PwdEncryption(driver1); pwd.passwordEncoder("Russia@411");
 * pwd.passwordDecoder("UnVzc2lhQDQxMQ=="); }
 ********************************************************************************************/