package com.ast.ejecutorws.args;

import org.kohsuke.args4j.Option;

import com.google.common.base.Optional;

/**
 * Clase contenedora de los argumentos recibidos por el programa
 * 
 * @author franco.milanese
 *
 */

public class ArgsData {

	public ArgsData() {
	}

	@Option(name = "-e", usage = "endpoint del web service a ejecutar", aliases = "--endpoint", handler = OptionalHandler.class)
	private Optional<String> endpoint = Optional.absent();

	@Option(name = "-r", usage = "trama del servicio a ejecutar", aliases = "--request", handler = OptionalHandler.class)
	private Optional<String> request = Optional.absent();

	@Option(name = "-a", usage = "SOAPAction del servicio a ejecutar", aliases = "--soapAction", handler = OptionalHandler.class)
	private Optional<String> soapAction = Optional.absent();

	@Option(name = "-ts", usage = "TrustStore a utilizar para SSL", aliases = "--trustStore", handler = OptionalHandler.class)
	private Optional<String> trustStore = Optional.absent();

	@Option(name = "-tp", usage = "Password del trustStore a utilizar para SSL", aliases = "--passwordTrust", handler = OptionalHandler.class)
	private Optional<String> trustStorePassword = Optional.absent();

	@Option(name = "-ks", usage = "KeyStore a utilizar para SSL", aliases = "--keyStore", handler = OptionalHandler.class)
	private Optional<String> keyStore = Optional.absent();

	@Option(name = "-kp", usage = "Password del KeyStore a utilizar para SSL", aliases = "--passwordKey", handler = OptionalHandler.class)
	private Optional<String> keyStorePassword = Optional.absent();

	@Option(name = "-c", usage = "comando a utilizar para la ejecucion", aliases = "--command", handler = OptionalHandler.class)
	private Optional<String> command = Optional.absent();

	@Option(name = "-p", usage = "Password a encriptar con el commando ENCRYPT", aliases = "--password", handler = OptionalHandler.class)
	private Optional<String> password = Optional.absent();
	
	
	
	
	@Option(name = "-x", usage = "auxiliar", aliases = "--auxiliar", handler = OptionalHandler.class)
	private Optional<String> auxilia = Optional.absent();
	
	@Option(name = "-ed", usage = "endpoint del 2do web service a ejecutar", aliases = "--endpointDos", handler = OptionalHandler.class)
	private Optional<String> endpoint2 = Optional.absent();
	
	@Option(name = "-ns", usage = "namespace", aliases = "--namespace", handler = OptionalHandler.class)
	private Optional<String> namespace = Optional.absent();
	
	@Option(name = "-sa2", usage = "soapAction2", aliases = "--soapAction2", handler = OptionalHandler.class)
	private Optional<String> soapAction2 = Optional.absent();
	
	@Option(name = "-mt", usage = "method", aliases = "--method", handler = OptionalHandler.class)
	private Optional<String> method = Optional.absent();
	
	
	

	public Optional<String> getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Optional<String> endpoint) {
		this.endpoint = endpoint;
	}
	
	public Optional<String> getEndpoint2() {
		return endpoint2;
	}

	public void setEndpoint2(Optional<String> endpoint2) {
		this.endpoint2 = endpoint2;
	}
	
	
	public Optional<String> getNamespace() {
		return namespace;
	}

	public void setNamespace(Optional<String> namespace) {
		this.namespace = namespace;
	}
	
	
	
	public Optional<String> getSoapAction2() {
		return soapAction2;
	}

	public void setSoapAction2(Optional<String> soapAction2) {
		this.soapAction2 = soapAction2;
	}	
	
	
	
	public Optional<String> getMethod() {
		return method;
	}

	public void setMethod(Optional<String> method) {
		this.method = method;
	}
	
	

	public Optional<String> getRequest() {
		return request;
	}

	public void setRequest(Optional<String> request) {
		this.request = request;
	}

	public Optional<String> getSoapAction() {
		return soapAction;
	}

	public void setSoapAction(Optional<String> soapAction) {
		this.soapAction = soapAction;
	}

	public Optional<String> getTrustStore() {
		return trustStore;
	}

	public void setTrustStore(Optional<String> trustStore) {
		this.trustStore = trustStore;
	}

	public Optional<String> getTrustStorePassword() {
		return trustStorePassword;
	}

	public void setTrustStorePassword(Optional<String> trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}

	public Optional<String> getKeyStore() {
		return keyStore;
	}

	public void setKeyStore(Optional<String> keyStore) {
		this.keyStore = keyStore;
	}

	public Optional<String> getKeyStorePassword() {
		return keyStorePassword;
	}

	public void setKeyStorePassword(Optional<String> keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}

	public Optional<String> getCommand() {
		return command;
	}

	public void setCommand(Optional<String> command) {
		this.command = command;
	}

	public Optional<String> getPassword() {
		return password;
	}

	public void setAuxiliar(Optional<String> password) {
		this.password = password;
	}
	
	public Optional<String> getAuxiliar() {
		return auxilia;
	}

	public void setPassword(Optional<String> auxilia) {
		this.auxilia = auxilia;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
