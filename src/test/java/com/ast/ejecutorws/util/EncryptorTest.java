package com.ast.ejecutorws.util;

import org.junit.Test;

public class EncryptorTest {

	@Test
	public void testEncryptWithIDString() {
		System.out.println(Encryptor.INSTANCE.encrypt("MPBKeyED","1041680","accusys123"));
	}

	@Test
	public void testDecryptWithIDString() {
		System.out.println(Encryptor.INSTANCE.decrypt("MPBKeyED","1041671","%27%D5%6F%84%E5%A9%4E%F8%29%68"));
	}
	
	@Test
	public void testEncryptString() {
		System.out.println("PASS:    "+Encryptor.INSTANCE.encrypt("accusys"));
	}
	
	@Test
	public void testDecryptString() {
		System.out.println(Encryptor.INSTANCE.decrypt("%ca%b2%be%05%67%dc%90%d1%2c%5e"));
	}

}
