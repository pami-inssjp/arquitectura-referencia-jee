package ar.gob.pami.jboss.jee.util;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.net.URL;

import javax.inject.Inject;

import org.apache.log4j.Logger;

public class FileReader {

	@Inject
	private Logger logger;

	public String readFile(String path) {
		ClassLoader classLoader = getClass().getClassLoader();
		// InputStream stream = classLoader.getResourceAsStream(path);
		try {
			URL pathToFile = classLoader.getResource(path);
			logger.info(pathToFile);
			InputStream stream = classLoader.getResourceAsStream(path);
			String content = this.convertStreamToString(stream);
			logger.info(content);
			return content;
		} catch (Exception e) {
			logger.error("Error al intentar de leer el archivo = " + path, e);
			return "";
		}
	}

	public byte[] readFileAsBytes(String path) {
		ClassLoader classLoader = getClass().getClassLoader();
		// InputStream stream = classLoader.getResourceAsStream(path);
		URL pathToFile = classLoader.getResource(path);
		logger.info(pathToFile);
		InputStream stream = classLoader.getResourceAsStream(path);
		byte[] fully = new byte[292];
		try {
			logger.info(String.format("available = %s", stream.available()));
			DataInputStream dis = new DataInputStream(stream);
			dis.readFully(fully);
			return fully;
		} catch (EOFException e) {
			logger.info(String.format("STREAM = %s", fully));
			fully[fully.length] = -1;
			return fully;
		} catch (Exception e) {
			logger.error("Error al intentar de leer el archivo = " + path, e);
			fully[fully.length] = -1;
			return fully;
		}
	}

	@SuppressWarnings("resource")
	private String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

}
