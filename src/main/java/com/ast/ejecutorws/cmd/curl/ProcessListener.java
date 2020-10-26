package com.ast.ejecutorws.cmd.curl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase para obtener la salida de un proceso-
 * 
 * @author franco.milanese
 *
 */
public class ProcessListener extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessListener.class);

	private Process proc;

	public ProcessListener(Process proc) {
		this.proc = proc;
		start();
	}

	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream(), Charset.forName("UTF-8")));
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				LOGGER.debug(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
