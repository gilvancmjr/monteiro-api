package com.monteiroapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monteiroapi.api.dto.VendaDto;
import com.monteiroapi.api.form.VendaForm;
import com.monteiroapi.domain.service.VendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/vendas", consumes = "application/json")
public class VendaController {
	
	//Não realizei as consultas com metodo Get pois acredito que Post fica mais facil de enteder que recebe um Body

	@Autowired
	private VendaService vendaService;

	@PostMapping
	public ResponseEntity<VendaDto> consultaMaiorValorVendido(@RequestBody @Valid VendaForm vendaForm) {

		VendaDto vendaDto = vendaService.encontrarMaiorValorVendido(vendaForm);

		return ResponseEntity.ok(vendaDto);

	}

}
