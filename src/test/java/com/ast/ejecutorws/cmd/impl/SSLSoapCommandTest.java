package com.ast.ejecutorws.cmd.impl;

import java.io.IOException;

import org.junit.Test;

import com.ast.ejecutorws.args.ArgsData;
import com.ast.ejecutorws.cmd.CommandWS;
import com.ast.ejecutorws.cmd.CommandWSException;
import com.google.common.base.Optional;

public class SSLSoapCommandTest {

	/*
	@Test
	public void testExecuteTramaFileSSLLocalhostSSL() throws IOException, InterruptedException, CommandWSException {
		ArgsData input = new ArgsData();
		input.setEndpoint(Optional.of("https://wks0110.accusysargbsas.local:9446/AST-PRUEBA/PruebaSSL"));
		input.setRequest(Optional.of("D:\\TFS2018\\Conector ECHEQ v1.0\\src\\test\\resources\\request2.xml"));
		input.setSoapAction(Optional.of("http://webservices.cts.ast/PruebaSSL/executeRequest"));
		input.setTrustStore(Optional.of("ks.jks"));
		input.setTrustStorePassword(Optional.of("%90%5c%1f%b4%a0%33%61%93%cd%65"));
		input.setKeyStore(Optional.of("ks.jks"));
		input.setKeyStorePassword(Optional.of("%90%5c%1f%b4%a0%33%61%93%cd%65"));
		CommandWS soapCommand = new SSLSoapCommand(input);
		soapCommand.execute();
	}

	@Test(expected = CommandWSException.class)
	public void testExecuteTramaFileSSLLocalhostSSLMissingKeyStorePass() throws IOException, InterruptedException, CommandWSException {
		ArgsData input = new ArgsData();
		input.setEndpoint(Optional.of("https://wks0110.accusysargbsas.local:9446/AST-PRUEBA/PruebaSSL"));
		input.setRequest(Optional.of("D:\\TFS2018\\Conector ECHEQ v1.0\\src\\test\\resources\\request2.xml"));
		input.setSoapAction(Optional.of("http://webservices.cts.ast/PruebaSSL/executeRequest"));
		input.setTrustStore(Optional.of("ks.jks"));
		input.setTrustStorePassword(Optional.of("%90%5c%1f%b4%a0%33%61%93%cd%65"));
		input.setKeyStore(Optional.of("ks.jks"));
		CommandWS soapCommand = new SSLSoapCommand(input);
		soapCommand.execute();
	}

	@Test
	public void testExecuteTramaXMLLocalhostSSLTrustStoreAndPassProvided() throws IOException, InterruptedException, CommandWSException {
		ArgsData input = new ArgsData();
		input.setEndpoint(Optional.of("https://wks0110.accusysargbsas.local:9446/AST-PRUEBA/PruebaSSL"));
		input.setRequest(Optional.of(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.cts.ast/\"><soapenv:Header/><soapenv:Body><web:execute><nombre>franco</nombre></web:execute></soapenv:Body></soapenv:Envelope>"));
		input.setSoapAction(Optional.of("http://webservices.cts.ast/PruebaSSL/executeRequest"));
		input.setTrustStore(Optional.of("ks.jks"));
		input.setTrustStorePassword(Optional.of("%90%5c%1f%b4%a0%33%61%93%cd%65"));
		input.setKeyStore(Optional.of("ks.jks"));
		input.setKeyStorePassword(Optional.of("%90%5c%1f%b4%a0%33%61%93%cd%65"));
		CommandWS soapCommand = new SSLSoapCommand(input);
		soapCommand.execute();
	}

	@Test(expected = CommandWSException.class)
	public void testExecuteTramaXMLLocalhostSSLTrustStoreAndPassProvidedAFIP() throws IOException, InterruptedException, CommandWSException {
		System.setProperty("javax.net.debug", "ssl");
		ArgsData input = new ArgsData();
		input.setEndpoint(Optional.of("https://wsaahomo.afip.gov.ar/ws/services/LoginCms"));
		input.setRequest(Optional.of(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsaa=\"http://wsaa.view.sua.dvadac.desein.afip.gov\"> <soapenv:Header/> <soapenv:Body> <wsaa:loginCms> <wsaa:in0>sadas</wsaa:in0> </wsaa:loginCms> </soapenv:Body> </soapenv:Envelope>"));
		input.setSoapAction(Optional.of("https://wsaahomo.afip.gov.ar/ws/services/LoginCms/LoginCMS/loginCmsRequest"));
		input.setTrustStore(Optional.of("wsaahomo.afip.gov.ar.jks"));
		input.setTrustStorePassword(Optional.of("%99%50%10%a0"));
		input.setKeyStore(Optional.of("CertCOC.jks"));
		input.setKeyStorePassword(Optional.of("%90%5c%1f%b4%a0%33%61"));
		// input.setTrustStore(Optional.of("ks.jks"));
		// input.setTrustStorePassword(Optional.of("%90%5c%1f%b4%a0%33%61%93%cd%65"));
		// input.setKeyStore(Optional.of("ks.jks"));
		// input.setKeyStorePassword(Optional.of("%90%5c%1f%b4%a0%33%61%93%cd%65"));
		CommandWS soapCommand = new SSLSoapCommand(input);
		soapCommand.execute();

	}

	*/
}
