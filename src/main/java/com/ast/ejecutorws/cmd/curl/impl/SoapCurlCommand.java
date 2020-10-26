package com.ast.ejecutorws.cmd.curl.impl;

import com.ast.ejecutorws.args.ArgsData;
import com.ast.ejecutorws.cmd.curl.AbstractCurlCommand;

/**
 * Clase para ejecutar WebServices SOAP a traves de CURL
 * 
 * @author franco.milanese
 *
 */
public class SoapCurlCommand extends AbstractCurlCommand {

	// private static final Logger LOGGER = LoggerFactory.getLogger(SoapCurlCommand.class);

	public SoapCurlCommand(ArgsData inputData) {
		super(inputData);
	}

	@Override
	public String[] getExecutionCommand() {
		return null;
		//TODO: implementar
//		String request = new File(inData.getRequest()).exists() ? "@" + inData.getRequest() : inData.getRequest();
//		return new String[] { "curl", "-s", "-S", "-X", "POST", "-H", "SOAPAction:", inData.getSoapAction(), "-H", "\"Content-Type: text/xml\"",
//				"--data-binary",
//				request, inData.getEndpoint() };
	}

}
