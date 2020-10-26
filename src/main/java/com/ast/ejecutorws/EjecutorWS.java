package com.ast.ejecutorws;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ast.ejecutorws.args.ArgsData;
import com.ast.ejecutorws.args.ArgumentParser;
import com.ast.ejecutorws.cmd.CommandWS;
import com.ast.ejecutorws.cmd.CommandWSException;
import com.ast.ejecutorws.cmd.CommandWSFactory;
import com.ast.ejecutorws.config.Configuration;
import com.google.common.base.Optional;

import ch.qos.logback.classic.spi.LoggerRemoteView;

/**
 * Clase Principal del Ejecutor WS
 * 
 * @author franco.milanese
 *
 */
public class EjecutorWS {

	private static final Logger LOGGER = LoggerFactory.getLogger(EjecutorWS.class);

	private static final String VERSION = "1.0.0";

	public static void main(String[] args) {
		
		//String[] args = { "-j", "/AFIL/O_DATANET_S*", "-c", "STATUS_JOB","-i","18/08/2020-12:12:12","-t","RFKK_MASS_ACT_SINGLE_JOB" };
		//String[] args = { "-j", "/AFIL/O_DATANET_S*", "-c", "STATUS_JOB", "-t","RFKK_MASS_ACT_SINGLE_JOB" };
		//arg = args;
		//String[] arg = { "-c","SOAP","-e","http://sujetosvipt.intranet.osde:8680/sujetovip-backend/webservices/UpdateService","-a","","-r","<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='http://services.sujetovip.osde.com.ar/'><soapenv:Header/><soapenv:Body><ser:start/></soapenv:Body></soapenv:Envelope>","-x","Ax"};
		//String[] arg = { "-c","REST","-e","http://sujetosvipt.intranet.osde:8680/sujetovip/batch/update/executions/start","-x",""};
		//args = arg;
		LOGGER.info("Iniciando Ejecutor WS - v" + VERSION);
		LOGGER.debug("java.library.path--------------------------------------------------------------------------------------------------------------");
		LOGGER.debug(System.getProperty("java.library.path"));
		LOGGER.debug("-------------------------------------------------------------------------------------------------------------------------------");
		LOGGER.debug(Arrays.toString(args));
		Configuration.loadConnectorConfig("wsexecutor.conf");
		// PARSE INPUT ARGUMENTS
		ArgsData inputArgs = ArgumentParser.parseArguments(args);

		// Create ExecutionCommand
		CommandWS command = CommandWSFactory.getCommand(inputArgs);
		try {
			// Ejecuta el commando construido
			LOGGER.info("Ejecutando Comando");
			command.execute();
			return;
		} catch (CommandWSException e) {
			LOGGER.error(e.getMessage());
		}

	}

}
