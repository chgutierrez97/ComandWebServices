package com.ast.ejecutorws.cmd;

public class CommandWSException extends Exception {

	private static final long serialVersionUID = 1268659968186667043L;

	public CommandWSException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandWSException(String message) {
		super(message);
	}

	public CommandWSException(Throwable cause) {
		super(cause);
	}

}
