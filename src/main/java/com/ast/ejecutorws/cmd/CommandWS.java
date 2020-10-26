package com.ast.ejecutorws.cmd;

import java.io.IOException;

public interface CommandWS {

	/**
	 * Ejecuta el comando construido como un proceso.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws CommandWSException 
	 */
	void execute() throws CommandWSException;

}
