package com.monteiroapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monteiroapi.domain.model.Venda;

public interface VendaRepository  extends JpaRepository<Venda, Long>{
	
	Venda findVendaByNomeVendedorAndPeriodoVenda(String nomeVendedor, String periodoVenda);

}
