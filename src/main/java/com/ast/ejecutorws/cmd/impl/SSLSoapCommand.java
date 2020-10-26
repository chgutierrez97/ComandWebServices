package com.ast.ejecutorws.cmd.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ast.ejecutorws.args.ArgsData;
import com.ast.ejecutorws.cmd.CommandWSException;
import com.ast.ejecutorws.config.Configuration;
import com.ast.ejecutorws.util.Encryptor;
import com.google.common.base.Optional;

public class SSLSoapCommand extends SoapCommand {

	private static final Logger LOGGER = LoggerFactory.getLogger(SSLSoapCommand.class);

	public SSLSoapCommand(ArgsData inputData) {
		super(inputData);
	}

	@Override
	public void execute() throws CommandWSException {
		CloseableHttpClient httpClient = null;
		if (!containsRequiredFields()) {
			throw new CommandWSException("Faltan campos requeridos");
		}
		try {
			httpClient = getSSLConfig();
			HttpPost httpPost = new HttpPost(inData.getEndpoint().get());
			httpPost.addHeader("SOAPAction", inData.getSoapAction().get());
			httpPost.setEntity(getHttpEntity());
			HttpResponse response = httpClient.execute(httpPost);
			System.out.println(EntityUtils.toString(response.getEntity()));
			httpClient.close();
		} catch (UnsupportedOperationException | KeyManagementException | NoSuchAlgorithmException | KeyStoreException
				| CertificateException | UnrecoverableKeyException | IllegalStateException | IOException e) {
			throw new CommandWSException(e.getMessage(), e);
		}
	}

	/**
	 * Devuelve una instancia {@link HttpClient} con las configuraciones de SSL seteadas.
	 * 
	 * @return una instancia de {@link HttpClient}
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws UnrecoverableKeyException
	 */
	private CloseableHttpClient getSSLConfig()
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException,
			IllegalStateException {
		Security.setProperty("ssl.SocketFactory.provider", "com.ibm.jsse2.SSLSocketFactoryImpl");
		Security.setProperty("ssl.ServerSocketFactory.provider", "com.ibm.jsse2.SSLServerSocketFactoryImpl");

		// TRUSTSTORE
		KeyStore ts = KeyStore.getInstance("JKS");
		try {
			FileInputStream tsStream = null;
			Optional<String> tsFile = inData.getTrustStore().or(Configuration.getTrustStore());
			tsStream = new FileInputStream(tsFile.get());
			String tsPasswordDecrypted = getTrustStorePassword();
			ts.load(tsStream, tsPasswordDecrypted.toCharArray());
			tsStream.close();
		} catch (IllegalStateException e) {
			LOGGER.error("Ocurrio un error al cargar el TrustStore");
			throw e;
		}

		// KEYSTORE
		KeyStore ks = KeyStore.getInstance("JKS");
		try {
			FileInputStream ksStream = null;
			Optional<String> ksFile = inData.getKeyStore().or(Configuration.getKeyStore());
			ksStream = new FileInputStream(ksFile.get());
			String ksPasswordDecrypted = getKeyStorePassword();
			inData.setKeyStorePassword(Optional.of(ksPasswordDecrypted));
			ks.load(ksStream, ksPasswordDecrypted.toCharArray());
			ksStream.close();
		} catch (IllegalStateException e) {
			LOGGER.error("Ocurrio un error al cargar el KeyStore");
			throw e;
		}

		SSLContext context = SSLContextBuilder.create()
				.loadTrustMaterial(ts, null)
				.loadKeyMaterial(ks, inData.getKeyStorePassword().get().toCharArray())
				.build();

		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(context, new String[] { "TLSv1.1", "TLSv1.2" },
				null,
				new DefaultHostnameVerifier());

		return HttpClientBuilder.create()/* .setProxy(new HttpHost("192.168.2.10", 8080, "http")) */
				.setSSLSocketFactory(sslConnectionSocketFactory)
				.build();

	}

	/**
	 * @param optional
	 * @return
	 */
	private String getTrustStorePassword() {
		try {
			if (Configuration.encryptionOn())
				return inData.getTrustStorePassword().isPresent() ? Encryptor.INSTANCE.decrypt(inData.getTrustStorePassword().get())
						: Encryptor.INSTANCE.decrypt(Configuration.getTrustStorePassword().get());
			else
				return inData.getTrustStorePassword().isPresent() ? inData.getTrustStorePassword().get()
						: Encryptor.INSTANCE.decrypt(Configuration.getTrustStorePassword().get());
		} catch (IllegalStateException e) {
			LOGGER.error("No se ha especificado password para el TrustStore");
			throw e;
		}
	}

	/**
	 * @param optional
	 * @return
	 */
	private String getKeyStorePassword() {
		try {
			if (Configuration.encryptionOn())
				return inData.getKeyStorePassword().isPresent() ? Encryptor.INSTANCE.decrypt(inData.getKeyStorePassword().get())
						: Encryptor.INSTANCE.decrypt(Configuration.getKeyStorePassword().get());
			else
				return inData.getKeyStorePassword().isPresent() ? inData.getKeyStorePassword().get()
						: Encryptor.INSTANCE.decrypt(Configuration.getKeyStorePassword().get());
		} catch (IllegalStateException e) {
			LOGGER.error("No se ha especificado password para el KeyStore");
			throw e;
		}
	}

}
