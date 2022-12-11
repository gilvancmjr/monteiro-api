package com.monteiroapi.domain.util;

public abstract class FormataPeriodo {

	public static int formataPeriodoEmDia(String Periodo) {
		int dia = Integer.parseInt(Periodo.substring(0, 2));
		return dia;

	}

	public static int formataPeriodoEmAno(String Periodo) {
		int ano = Integer.parseInt(Periodo.substring(3, 7));
		return ano;

	}

}
