package com.ast.ejecutorws.cmd.impl;

import com.ast.ejecutorws.args.ArgumentParser;
import com.ast.ejecutorws.cmd.CommandWS;
import com.ast.ejecutorws.cmd.CommandWSException;

public class HelpCommand implements CommandWS {

	public HelpCommand log(String msg) {
		System.out.println(msg);
		return this;
	}

	@Override
	public void execute() throws CommandWSException {
		log("\nEstructura del comando:\n");
		log("\n\tExecutorWS -e (-r) [-a]\n\n");
		ArgumentParser.printUsage(System.out);
		log("\nEjemplos:\n");
		log(
				"\n\tExecutorWS -c SOAP -e http://IP:PUERTO/CONTEXT_ROOT/PruebaWSService -r \"D:\\Prueba\\request.xml\" -a http://service.prueba.ecobis.cobiscorp.ws/Prueba/PruebaOp1");
		log("\n\tExecutorWS -c SOAP -e https://IP:PUERTO/CONTEXT_ROOT/PruebaWSService -r \"D:\\Prueba\\request.xml\" -a http://service.prueba.ecobis.cobiscorp.ws/Prueba/PruebaOp1 -ts D:/../truststore.jks -tp PASSWORD -ks D:/../keystore.jks -kp PASSWORD\n\n ");
		log("\n\tExecutorWS -c ENCRYPT -p PASSWORD\n\n ");

	}

}
