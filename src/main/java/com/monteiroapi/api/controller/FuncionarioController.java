package com.monteiroapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public FuncionarioDto consultaValorTotalPagoSalarioBeneficio(@RequestBody @Valid FuncionarioForm funcionarioForm) {
		return funcionarioService.calculaValorTotalPagoSalarioBeneficio(funcionarioForm);
	}

	@PostMapping("/valor-total-pago-salario")
	public FuncionarioDto consultaValorTotalPagoSalario(@RequestBody @Valid FuncionarioForm funcionarioForm) {
		return funcionarioService.calculaValorTotalPagoSalario(funcionarioForm);
	}

	@PostMapping("/valor-total-pago-beneficio")
	public FuncionarioDto consultaValorTotalPagoBeneficio(@RequestBody @Valid FuncionarioForm funcionarioForm) {
		return funcionarioService.calcularValorTotalPagoBeneficio(funcionarioForm);
	}

	@PostMapping("/maior-valor-total-pago-salario-beneficio")
	public FuncionarioDto consultarMaiorValorTotalPagoSalarioBeneficio(
			@RequestBody @Valid FuncionarioForm funcionarioForm) {
		return funcionarioService.encontrarMaiorValorTotalPagoSalarioBeneficio(funcionarioForm);
	}

	@PostMapping("/nome-funcionario-valor-total-pago-salario-beneficio")
	public FuncionarioDto consultarNomeDoFuncionarioValorTotalPagoBeneficio(
			@RequestBody @Valid FuncionarioForm funcionarioForm) {
		return funcionarioService.encontrarNomeDoFuncionarioValorTotalPagoBeneficio(funcionarioForm);
	}
}
