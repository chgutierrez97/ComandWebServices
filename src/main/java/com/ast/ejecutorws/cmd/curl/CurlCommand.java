package com.ast.ejecutorws.cmd.curl;

import com.ast.ejecutorws.cmd.CommandWS;

/**
 * Interfaz para los distintos tipos de commandos a correr con CURL.
 * 
 * @author franco.milanese
 *
 */
public interface CurlCommand extends CommandWS {

	/**
	 * Arma el comando a ejecutar.
	 * 
	 * @return
	 */
	String[] getExecutionCommand();
	
}
