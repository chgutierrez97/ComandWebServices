/**
 * 
 */
package com.ast.ejecutorws.cmd.exception;

/**
 * Excepcion lanzada ante la falta de un parametro requerido
 * 
 * @author franco.milanese
 *
 */
public class RequiredParameterException extends Exception {

	private static final long serialVersionUID = 5011419282115254788L;


	public RequiredParameterException(String message) {
		super(message);
	}

	public RequiredParameterException(Throwable cause) {
		super(cause);
	}

	public RequiredParameterException(String message, Throwable cause) {
		super(message, cause);
	}


}
