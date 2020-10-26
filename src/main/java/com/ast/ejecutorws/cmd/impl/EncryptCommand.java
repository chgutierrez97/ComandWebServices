/**
 * 
 */
package com.ast.ejecutorws.cmd.impl;

import com.ast.ejecutorws.args.ArgsData;
import com.ast.ejecutorws.cmd.AbstractCommandWS;
import com.ast.ejecutorws.cmd.CommandWSException;
import com.ast.ejecutorws.util.Encryptor;

/**
 * Clase para la encriptacion de passwords
 * 
 * @author franco.milanese
 *
 */
public class EncryptCommand extends AbstractCommandWS {
	
	

	/**
	 * @param inputData
	 */
	public EncryptCommand(ArgsData inputData) {
		super(inputData);
	}

	@Override
	public void execute() throws CommandWSException {
		if (!containsRequiredFields()) {
			throw new CommandWSException("Faltan campos requeridos");
		}
		String encryptedPassword = Encryptor.INSTANCE.encrypt(inData.getPassword().get());
		System.out.println(encryptedPassword);
	}

	@Override
	public boolean containsRequiredFields() {
		return inData.getPassword().isPresent();
	}


}
