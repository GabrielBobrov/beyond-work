package br.com.gabrielbobrov.beyondwork.util;

import java.util.Collection;

public class StringUtils {

	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		
		return str.trim().length() == 0;
	}
	
	/*public static String encrypt(String rawString) {
		if (isEmpty(rawString)) {
			return null;
		}
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder.encode(rawString);
	}*/ //TODO: encrypt passowrd
	
	public static String concatenate(Collection<String> strings) {
		
		if(strings == null || strings.size() == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();//string builder concatena strings
		String delimiter = ", ";
		boolean first = true;
		
		for (String string : strings) {
			if(!first) {
				sb.append(delimiter);
			}
			
			sb.append(string);
			first = false;
		}
		return sb.toString();
	}

}
