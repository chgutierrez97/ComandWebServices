package com.ast.ejecutorws.misc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLServerSocketFactory;

public class PruebaSSL {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

		System.setProperty("javax.net.ssl.keyStore", "prueba_accusys.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "accusys123");
		System.setProperty("javax.net.ssl.trustStore", "prueba_accusys.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "accusys123");
		int puerto = 9999;
		ServerSocket ssocket = SSLServerSocketFactory.getDefault().createServerSocket(puerto);
		System.out.println("ESCUCHANDO PUERTO " + puerto);
		Socket socket = ssocket.accept();

		BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
		byte[] buffer = new byte[1024];
		int read;
		while ((read = bis.read()) != -1) {
			String output = new String(buffer, 0, read);
			System.out.print(output);
			System.out.flush();
		}
		;
		socket.close();

	}

}
