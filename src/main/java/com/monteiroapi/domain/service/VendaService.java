package com.monteiroapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monteiroapi.api.assembler.VendaAssembler;
import com.monteiroapi.api.dto.VendaDto;
import com.monteiroapi.api.form.VendaForm;
import com.monteiroapi.domain.exception.EntidadeNaoEncontradaException;
import com.monteiroapi.domain.model.Venda;
import com.monteiroapi.domain.repository.VendaRepository;
import com.monteiroapi.domain.util.FormataPeriodo;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private VendaAssembler vendaAssembler;

	public VendaDto encontrarMaiorValorVendido(VendaForm vendaForm) {

		int anoConsulta = FormataPeriodo.formataPeriodoEmAno(vendaForm.getPeriodo());
		int mesConsulta = FormataPeriodo.formataPeriodoEmDia(vendaForm.getPeriodo());
		Double maiorVenda = 0.0;
		VendaDto vendaDto = new VendaDto();
		Venda venda;
		try {
			for (String nome : vendaForm.getNomes()) {

				venda = buscarOuFalhar(nome, vendaForm.getPeriodo());
				int anoVenda = FormataPeriodo.formataPeriodoEmAno(vendaForm.getPeriodo());
				int mesVenda = FormataPeriodo.formataPeriodoEmDia(vendaForm.getPeriodo());
				if (anoVenda == anoConsulta && mesVenda == mesConsulta) {
					if (maiorVenda < venda.getValorVenda()) {
						maiorVenda = venda.getValorVenda();
						vendaDto = vendaAssembler.toModel(venda);
					}

				}

			}
			return vendaDto;
		} catch (NullPointerException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}

	}

	private Venda buscarOuFalhar(String nome, String periodo) {
		Venda vendas;
		vendas = vendaRepository.findVendaByNomeVendedorAndPeriodoVenda(nome, periodo);
		if (vendas == null) {
			throw new EntidadeNaoEncontradaException(nome);
		}
		return vendas;
	}

}
