package com.monteiroapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FuncionarioNaoEncontradoException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FuncionarioNaoEncontradoException(String mensagem) {
		super(mensagem);

	}
	public FuncionarioNaoEncontradoException(Long estadoId) {
		this(String.format("Não existe funcionario com o nome %d", estadoId));
	}
}
