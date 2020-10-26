package com.ast.ejecutorws.cmd;

import com.ast.ejecutorws.args.ArgsData;
import com.ast.ejecutorws.cmd.impl.EncryptCommand;
import com.ast.ejecutorws.cmd.impl.HelpCommand;
import com.ast.ejecutorws.cmd.impl.RestCommand;
import com.ast.ejecutorws.cmd.impl.SSLSoapCommand;
import com.ast.ejecutorws.cmd.impl.SoapCommand;

public class CommandWSFactory {

	private CommandWSFactory() {

	}

	public static CommandWS getCommand(ArgsData inputArgs) {
		String command = inputArgs.getCommand().isPresent() ? inputArgs.getCommand().get() : "HELP";

		switch (command) {
			// case "SOAP_CURL":
			// return new SoapCurlCommand(inputArgs);
			case "SOAP":
				return inputArgs.getEndpoint().get().startsWith("https") ? new SSLSoapCommand(inputArgs) : new SoapCommand(inputArgs);
				//return new SoapCommand(inputArgs);
			case "REST":
				return new RestCommand(inputArgs);
				//return new RestCommand(inputArgs);
			case "ENCRYPT":
				return new EncryptCommand(inputArgs);	
			default:
				return new HelpCommand();
		}

	}

}
