package com.ast.ejecutorws.cmd.curl;

import java.io.IOException;

import com.ast.ejecutorws.args.ArgsData;
import com.ast.ejecutorws.cmd.CommandWSException;

/**
 * Clase abstracta a partir de la que extienden los comandos CURL. 
 * @author franco.milanese
 *
 */
public abstract class AbstractCurlCommand implements CurlCommand {

	protected final ArgsData inData;
	
	public AbstractCurlCommand(ArgsData inputData) {
		this.inData = inputData;
	}

	public void execute() throws CommandWSException {
		try {
			ProcessListener processListener = null;
			Process process;
			process = new ProcessBuilder().redirectErrorStream(true).command(getExecutionCommand()).start();
			process.waitFor();
			processListener = new ProcessListener(process);
			processListener.join();
		} catch (IOException | InterruptedException e) {
			throw new CommandWSException("Error al ejecutar el proceso Curl", e);
			
		}
	}
	
}
