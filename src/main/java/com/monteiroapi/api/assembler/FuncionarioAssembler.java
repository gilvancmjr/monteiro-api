package com.monteiroapi.api.assembler;

import org.springframework.stereotype.Component;

import com.monteiroapi.api.dto.FuncionarioDto;
import com.monteiroapi.domain.util.FormataValor;

@Component
public class FuncionarioAssembler {

	public FuncionarioDto toModel(Double valorTotal) {
		String valorMoedaReal = FormataValor.real(valorTotal);
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		funcionarioDto.setValorTotal(valorMoedaReal);

		return funcionarioDto;
	}

	public FuncionarioDto toModel(String nome) {
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		funcionarioDto.setNome(nome);

		return funcionarioDto;
	}

}
