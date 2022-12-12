package com.monteiroapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monteiroapi.api.dto.VendaDto;
import com.monteiroapi.api.form.VendaForm;
import com.monteiroapi.domain.service.VendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/vendas")
public class VendaController {

	@Autowired
	private VendaService vendaService;

	@PostMapping
	public VendaDto consultaMaiorValorVendido(@RequestBody @Valid VendaForm vendaForm) {

		return vendaService.encontrarMaiorValorVendido(vendaForm);

	}



}
