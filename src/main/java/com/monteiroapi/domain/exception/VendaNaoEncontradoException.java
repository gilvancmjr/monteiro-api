package com.monteiroapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VendaNaoEncontradoException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VendaNaoEncontradoException(String mensagem) {
		super(mensagem);

	}
	public VendaNaoEncontradoException(Long estadoId) {
		this(String.format("Não existe vendedor com o nome %d", estadoId));
	}
}
