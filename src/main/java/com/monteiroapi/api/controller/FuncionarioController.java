package com.monteiroapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monteiroapi.api.dto.FuncionarioDto;
import com.monteiroapi.api.form.FuncionarioForm;
import com.monteiroapi.domain.service.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping("/valor-total-pago-salario-beneficio")
	public ResponseEntity<FuncionarioDto> consultaValorTotalPagoSalarioBeneficio(
			@RequestBody @Valid FuncionarioForm funcionarioForm) {
		FuncionarioDto funcionarioDto = funcionarioService.calculaValorTotalPagoSalarioBeneficio(funcionarioForm);
		return ResponseEntity.ok(funcionarioDto);
	}

	@PostMapping("/valor-total-pago-salario")
	public ResponseEntity<FuncionarioDto> consultaValorTotalPagoSalario(
			@RequestBody @Valid FuncionarioForm funcionarioForm) {
		FuncionarioDto funcionarioDto = funcionarioService.calculaValorTotalPagoSalario(funcionarioForm);
		return ResponseEntity.ok(funcionarioDto);
	}

	@PostMapping("/valor-total-pago-beneficio")
	public ResponseEntity<FuncionarioDto> consultaValorTotalPagoBeneficio(
			@RequestBody @Valid FuncionarioForm funcionarioForm) {
		FuncionarioDto funcionarioDto = funcionarioService.calcularValorTotalPagoBeneficio(funcionarioForm);
		return ResponseEntity.ok(funcionarioDto);
	}

	@PostMapping("/maior-valor-total-pago-salario-beneficio")
	public ResponseEntity<FuncionarioDto> consultarMaiorValorTotalPagoSalarioBeneficio(
			@RequestBody @Valid FuncionarioForm funcionarioForm) {
		FuncionarioDto funcionarioDto = funcionarioService
				.encontrarMaiorValorTotalPagoSalarioBeneficio(funcionarioForm);
		return ResponseEntity.ok(funcionarioDto);
	}

	@PostMapping("/nome-funcionario-valor-total-pago-salario-beneficio")
	public ResponseEntity<FuncionarioDto> consultarNomeDoFuncionarioValorTotalPagoBeneficio(
			@RequestBody @Valid FuncionarioForm funcionarioForm) {
		FuncionarioDto funcionarioDto = funcionarioService
				.encontrarNomeDoFuncionarioValorTotalPagoBeneficio(funcionarioForm);
		return ResponseEntity.ok(funcionarioDto);
	}
}
