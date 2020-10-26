package com.ast.ejecutorws.args;

/**
 * Excepcion al parsear los argumentos del programa.
 * 
 * @author franco.milanese
 *
 */
public class ArgumentParserException extends RuntimeException {

	private static final long serialVersionUID = 896371397137110577L;
	
	public ArgumentParserException (String message) {
		super(message);
	}
	
	public ArgumentParserException (Throwable cause) {
		super(cause);
	}
	
	public ArgumentParserException (String message, Throwable cause) {
		super(message, cause);
	}

}
