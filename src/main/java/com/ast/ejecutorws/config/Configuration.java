package com.ast.ejecutorws.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

/**
 * Configuraciones del componente.
 * 
 * @author franco.milanese
 *
 */
public class Configuration {

	private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);
	private static Properties config = new Properties();

	public static void loadConnectorConfig(String fileName) {
		config = new Properties();
		FileInputStream fileInputStream = null;
		try {
			LOGGER.info("Cargando configuraciones desde {}", new File(fileName).getAbsolutePath());
			fileInputStream = new FileInputStream(fileName);
			config.load(fileInputStream);
			LOGGER.info("Cargandas configuraciones desde {}", new File(fileName).getAbsolutePath());
		} catch (Exception e) {
			LOGGER.warn("No se pudo cargar el archivo {}. Se utilizarán las configuraciones por defecto.", new File(fileName).getAbsolutePath());
			config = new Properties();
		} finally {
			if (Optional.fromNullable(fileInputStream).isPresent()) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String getProperty(String key, String... def) {
		return def.length == 0 ? config.getProperty(key) : config.getProperty(key, def[0]);
	}

	public static boolean devModeOn() {
		return config.getProperty("dev_mode_on", "false").equalsIgnoreCase("true");
	}

	public static boolean encryptionOn() {
		return config.getProperty("encryption_on", "true").equalsIgnoreCase("true");
	}

	public static Optional<String> getTrustStore() {
		return Optional.fromNullable(config.getProperty("truststore"));
	}

	public static Optional<String> getTrustStorePassword() {
		return Optional.fromNullable(config.getProperty("truststore_password"));
	}

	public static Optional<String> getKeyStore() {
		return Optional.fromNullable(config.getProperty("keystore"));
	}

	public static Optional<String> getKeyStorePassword() {
		return Optional.fromNullable(config.getProperty("keystore_password"));
	}

}
