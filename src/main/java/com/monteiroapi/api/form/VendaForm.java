package com.monteiroapi.api.form;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaForm {

	
	private List<String> nomes;
	@NotBlank
	private String periodo;

}
