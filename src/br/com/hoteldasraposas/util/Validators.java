package br.com.hoteldasraposas.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validators {
	/**
	 * Verifica a validade de um CPF
	 * 
	 * Internamente, converte o Long para uma String e chama a função {@link #validateCpf(String)}
	 * 
	 * @param cpf Long contendo o CPF a ser validado
	 * @return true caso for válido, false caso contrário
	 */
	public static boolean validateCpf(Long cpf) {
		// Converte o número para uma string de no mínimo 11 caracteres
		return validateCpf(String.format("%1$011d", cpf));
	}
	
	/**
	 * Verifica a validade de um CPF
	 * @param cpf String contendo o CPF a ser validado
	 * @return true caso for válido, false caso contrário 
	 */
	public static boolean validateCpf(String cpf) {
		// Retira pontos e traços do cpf
		Pattern p = Pattern.compile("\\D");
		Matcher m = p.matcher(cpf);
		cpf = m.replaceAll("");
		
		if (cpf.length() != 11)
			return false;
		
		// Verifica se todos os dígitos não são repetidos
		Pattern p2 = Pattern.compile("0{11}|1{11}|2{11}|3{11}|4{11}|5{11}|6{11}|7{11}|8{11}|9{11}");
		m = p2.matcher(cpf);
		if (m.matches())
			return false;
		
		// Valida o primeiro dígito
		int sum = 0;
		for (int i = 0; i < 9; ++i)
			sum += (cpf.charAt(i) - '0') * (10 - i);
		int check = 11 - (sum % 11);
		if (check >= 10)
			check = 0;
		if (check != (cpf.charAt(9) - '0'))
			return false;
		
		// Valida segundo dígito
		sum = 0;
		for (int i = 0; i < 10; ++i)
			sum += (cpf.charAt(i) - '0') * (11 - i);
		check = 11 - (sum % 11);
		if (check >= 10)
			check = 0;
		if (check != (cpf.charAt(10) - '0'))
			return false;
		
		return true;
	}
}
