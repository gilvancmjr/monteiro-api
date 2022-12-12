package com.monteiroapi;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.monteiroapi.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest
class VendaTests {

	private String form;

	private String formEntidadeNaoEncontrada;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		form = ResourceUtils.getContentFromResource("/json/correto/venda-form.json");
		formEntidadeNaoEncontrada = ResourceUtils
				.getContentFromResource("/json/incorreto/venda-form-funcionario-sem-venda.json");

	}

	@Test
	public void deveRetornarSucesso_QuandoBuscarVendedorComMaiorValorVendido() {

		RestAssured.given().basePath("/vendas").port(8080).body(form).accept(ContentType.JSON)
				.contentType(ContentType.JSON).when().post().then().statusCode(200);

	}

	@Test
	public void deveRetornar404_QuandoBuscarVendedorComMaiorValorVendido() {

		RestAssured.given().basePath("/vendas").port(8080).body(formEntidadeNaoEncontrada).accept(ContentType.JSON)
				.contentType(ContentType.JSON).when().post().then().statusCode(HttpStatus.NOT_FOUND.value())
				.body("titulo", Matchers.equalTo("Recurso não encontrado"));

	}

}
