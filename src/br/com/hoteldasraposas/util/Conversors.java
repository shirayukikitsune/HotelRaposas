package br.com.hoteldasraposas.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Conversors {
	/**
	 * Converte uma string contendo um CPF para um Long
	 * @param entrada String contendo o CPF a ser convertido
	 * @throws NumberFormatException
	 */
	static public Long convertStringToCpf(String in)
			throws NumberFormatException {
		// Retira pontos e traços do CPF
		Pattern p = Pattern.compile("\\D");
		Matcher m = p.matcher(in);
		in = m.replaceAll("");
		
		if (in.length() != 11)
			throw new NumberFormatException("CPF com numeração faltante");
		
		return Long.parseLong(in);
	}
}
