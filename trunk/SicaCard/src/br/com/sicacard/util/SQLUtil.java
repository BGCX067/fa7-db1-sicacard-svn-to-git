/**
 *
 */
package br.com.sicacard.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * SQLUtil.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 18/04/2012
 * @version 1.0
 */
public class SQLUtil {

	private static Map<String, String> querys = new HashMap<String, String>();

	static{
		ResourceBundle propertie = ResourceBundle.getBundle("sql_query");
		Enumeration<String> chaves = propertie.getKeys();
		while(chaves.hasMoreElements()) {
			String chave = chaves.nextElement();
			querys.put(chave, propertie.getString(chave));
		}
	}

	/**
	 * Método responsável por obter as querys do arquivo
	 * de properties.
	 *
	 * @param chave String
	 * @return String
	 */
	public static String getQuery(String chave) {
		return querys.get(chave);
	}

}
