package com.ast.ejecutorws.cmd;

import com.ast.ejecutorws.args.ArgsData;

/**
 * Clase abstracta a partir de la que extienden los comandos.
 * 
 * @author franco.milanese
 *
 */
public abstract class AbstractCommandWS implements CommandWS{

	protected final ArgsData inData;

	public AbstractCommandWS(ArgsData inputData) {
		this.inData = inputData;
	}
	
	protected abstract boolean containsRequiredFields();
	
}
