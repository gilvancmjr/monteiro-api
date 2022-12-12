package com.monteiroapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.monteiroapi.api.dto.VendaDto;
import com.monteiroapi.api.form.VendaForm;
import com.monteiroapi.domain.exception.EntidadeNaoEncontradaException;
import com.monteiroapi.domain.service.VendaService;

@SpringBootTest
class VendaTests {

	@Autowired
	private VendaService vendaService;

	@Test
	public void deveConsultaComSucessoPorVendedoresComMaiorVendaNoMes() {

		List<String> nomes = new ArrayList<>();
		nomes.add("Ana Silva");
		nomes.add("João Mendes");
		VendaForm vendaForm = new VendaForm();
		vendaForm.setNomes(nomes);
		vendaForm.setPeriodo("12/2021");
		VendaDto vendaDto;

		vendaDto = vendaService.encontrarMaiorValorVendido(vendaForm);

		assertThat(vendaDto.getNome()).isNotNull();
		assertThat(vendaDto.getPeriodo()).isNotNull();
		assertThat(vendaDto.getValor()).isNotNull();

	}

	@Test
	public void deveFalharQuandoConsultaPorVendedoresSemDadosNoBanco() {

		List<String> nomes = new ArrayList<>();
		nomes.add("Gilvan Junior");

		VendaForm vendaForm = new VendaForm();
		vendaForm.setNomes(nomes);
		vendaForm.setPeriodo("12/2021");

		EntidadeNaoEncontradaException erroEsperado = Assertions.assertThrows(EntidadeNaoEncontradaException.class,
				() -> {
					vendaService.encontrarMaiorValorVendido(vendaForm);
				});

		assertThat(erroEsperado).isNotNull();

	}

}
