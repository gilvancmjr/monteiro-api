package com.monteiroapi.api.assembler;

import org.springframework.stereotype.Component;

import com.monteiroapi.api.dto.VendaDto;
import com.monteiroapi.domain.model.Venda;
import com.monteiroapi.domain.util.FormataValor;

@Component
public class VendaAssembler {

	public VendaDto toModel(Venda venda) {
		String valorMoedaReal = FormataValor.real(venda.getValorVenda());
		
		VendaDto vendaDto = new VendaDto();
		vendaDto.setNome(venda.getNomeVendedor());
		vendaDto.setPeriodo(venda.getPeriodoVenda());
		vendaDto.setValor(valorMoedaReal);

		return vendaDto;

	}

}
