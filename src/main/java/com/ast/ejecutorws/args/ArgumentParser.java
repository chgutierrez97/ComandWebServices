package com.ast.ejecutorws.args;

import java.io.OutputStream;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase para el parseo de argumentos del programa.
 * 
 * @author franco.milanese
 *
 */
public class ArgumentParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArgumentParser.class);

	private ArgumentParser() {

	}

	/**
	 * Parsea los argumentos recibidos por el programa, populando el objeto {@link ArgsData}.
	 * 
	 * @param args
	 *            los argumentos recibidos por el programa
	 * @return
	 * @throws ArgumentParserException
	 */
	public static ArgsData parseArguments(String... args) throws ArgumentParserException {
		ArgsData dataHolder = new ArgsData();
		CmdLineParser parser = new CmdLineParser(dataHolder);
		try {
			parser.parseArgument(args);
		} catch (CmdLineException e) {
			LOGGER.error(e.getMessage());
			ArgumentParser.printUsage();
			throw new ArgumentParserException(e);
		}
		return dataHolder;
	}

	/**
	 * Imprime informacion util para que el usuario pueda usar el programa.
	 * 
	 * @param outStream
	 *            stream por el que debe imprimir la informacion (ej: System.out o System.err)
	 */
	public static void printUsage(OutputStream... outStream) {
		OutputStream out = outStream.length == 0 ? System.out : outStream[0];
		// try {
		// out.write("\nEstructura del comando:\n".getBytes());
		// out.write("\n\tExecutorWS -e (-s||-f) [-a]\n\n".getBytes());
		CmdLineParser parser = new CmdLineParser(new ArgsData());
		parser.printUsage(out);
		// out.write("\nEjemplo:\n".getBytes());
		// out.write(
		// "\n\tExecutorWS -e http://IP:PUERTO/CONTEXT_ROOT/PruebaWSService -s \"<soapenv...>...</soapenv>\" -a
		// http://service.prueba.ecobis.cobiscorp.ws/Prueba/PruebaOp1\n\n"
		// .getBytes());
		// } catch (IOException e) {
		// LOGGER.error(e.getMessage());
		// }

	}

}
