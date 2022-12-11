package com.monteiroapi.api.form;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioForm {

	private List<String> nomes;
	@NotBlank
	private String periodo;

}
