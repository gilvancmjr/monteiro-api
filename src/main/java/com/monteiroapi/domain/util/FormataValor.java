package com.monteiroapi.domain.util;

import java.text.NumberFormat;

public abstract class FormataValor {

	public static String real(Double valor) {
		String real = NumberFormat.getCurrencyInstance().format(valor);
		return real;
	}
}
