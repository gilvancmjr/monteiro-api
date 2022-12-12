package com.monteiroapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);

	}
	public EntidadeNaoEncontradaException(Long estadoId) {
		this(String.format("Não existe funcionario com o nome %d", estadoId));
	}
}
