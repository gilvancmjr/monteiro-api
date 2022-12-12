package com.monteiroapi.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.monteiroapi.domain.exception.FuncionarioNaoEncontradoException;
import com.monteiroapi.domain.exception.VendaNaoEncontradoException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
			+ "o problema persistir, entre em contato com o administrador do sistema.";
	
	@ExceptionHandler(FuncionarioNaoEncontradoException.class)
	public ResponseEntity<?> tratarFuncionarioNaoEncontrada(FuncionarioNaoEncontradoException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O funcionario com " + 
				ex.getMessage() + " não encontrado.");
		Problem problem = Problem.builder()
				.dataHora(LocalDateTime.now())
				.status(status.value())
				.tipo(problemType.getUri())
				.titulo(problemType.getTitle())
				.detalhe(detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(VendaNaoEncontradoException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontrada(VendaNaoEncontradoException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O vendedor com " + 
				ex.getMessage() + " não encontrado.");
		Problem problem = Problem.builder()
				.dataHora(LocalDateTime.now())
				.status(status.value())
				.tipo(problemType.getUri())
				.titulo(problemType.getTitle())
				.detalhe(detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		
		if (body instanceof String) {
			body = Problem.builder().dataHora(LocalDateTime.now()).titulo((String) body).status(statusCode.value()).build();
		}
		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}

}
