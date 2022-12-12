package com.monteiroapi.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monteiroapi.api.assembler.FuncionarioAssembler;
import com.monteiroapi.api.dto.FuncionarioDto;
import com.monteiroapi.api.form.FuncionarioForm;
import com.monteiroapi.domain.exception.FuncionarioNaoEncontradoException;
import com.monteiroapi.domain.model.Funcionario;
import com.monteiroapi.domain.model.Venda;
import com.monteiroapi.domain.repository.FuncionarioRepository;
import com.monteiroapi.domain.repository.VendaRepository;
import com.monteiroapi.domain.util.Tempo;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private FuncionarioAssembler funcionarioAssembler;

	@Autowired
	private VendaRepository vendaRepository;

	public FuncionarioDto calculaValorTotalPagoSalarioBeneficio(FuncionarioForm funcionarioForm) {

		List<Funcionario> funcionarios = new ArrayList<>();
		Double valorTotal = 0.0;

		for (String nome : funcionarioForm.getNomes()) {

			funcionarios = buscarOuFalhar(nome);
			valorTotal += getSalario(funcionarios, funcionarioForm);
			valorTotal += getBeneficio(funcionarios, funcionarioForm);
		}

		return funcionarioAssembler.toModel(valorTotal);

	}

	public FuncionarioDto calculaValorTotalPagoSalario(FuncionarioForm funcionarioForm) {

		List<Funcionario> funcionarios = new ArrayList<>();
		Double valorTotal = 0.0;

		for (String nome : funcionarioForm.getNomes()) {

			funcionarios = buscarOuFalhar(nome);
			valorTotal += getSalario(funcionarios, funcionarioForm);
		}

		return funcionarioAssembler.toModel(valorTotal);
	}

	public FuncionarioDto calcularValorTotalPagoBeneficio(FuncionarioForm funcionarioForm) {

		List<Funcionario> funcionarios = new ArrayList<>();
		Double valorTotal = 0.0;

		for (String nome : funcionarioForm.getNomes()) {

			funcionarios = buscarOuFalhar(nome);
			
			valorTotal += getBeneficio(funcionarios, funcionarioForm);
		}

		return funcionarioAssembler.toModel(valorTotal);

	}

	public FuncionarioDto encontrarMaiorValorTotalPagoSalarioBeneficio(FuncionarioForm funcionarioForm) {

		List<Funcionario> funcionarios = new ArrayList<>();

		Double maiorValorTotal = 0.0;

		for (String nome : funcionarioForm.getNomes()) {

			funcionarios = buscarOuFalhar(nome);

			Double valorTotal = getBeneficio(funcionarios, funcionarioForm) + getSalario(funcionarios, funcionarioForm);

			if (maiorValorTotal < valorTotal) {
				maiorValorTotal = valorTotal;
			}

		}

		return funcionarioAssembler.toModel(maiorValorTotal);
	}

	public FuncionarioDto encontrarNomeDoFuncionarioValorTotalPagoBeneficio(FuncionarioForm funcionarioForm) {

		List<Funcionario> funcionarios = new ArrayList<>();
		String nomeFuncionario = "";
		Double maiorValorTotal = 0.0;

		for (String nome : funcionarioForm.getNomes()) {

			funcionarios = buscarOuFalhar(nome);
			for (Funcionario funcionario : funcionarios) {
				Double valorTotal = getBeneficio(funcionarios, funcionarioForm);
				if (maiorValorTotal < valorTotal) {
					nomeFuncionario = funcionario.getNome();
					maiorValorTotal = valorTotal;

				}
			}

		}

		return funcionarioAssembler.toModel(nomeFuncionario);

	}

	private Double getSalario(List<Funcionario> funcionarios, FuncionarioForm funcionarioForm) {
		Double salario = 0.0;

		for (Funcionario funcionario : funcionarios) {

			int tempoServico = Tempo.tempoServicoAno(funcionario.getPeriodoContratacao(), funcionarioForm.getPeriodo());

			salario = funcionario.getCargo().getSalarioInicial()
					+ (funcionario.getCargo().getAumentoSalarioAno() * tempoServico);

		}

		return salario;
	}

	private Double getBeneficio(List<Funcionario> funcionarios, FuncionarioForm funcionarioForm) {
		Double beneficio = 0.0;

		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getCargo().getNomeCargo().equals("Vendedor")) {
				Double beneficioVendedor = getBeneficioVendedor(funcionario.getNome(), funcionarioForm)
						* funcionario.getCargo().getBeneficio();
				beneficio = beneficioVendedor;
				return beneficio;
			}

			Double salario = getSalario(funcionarios, funcionarioForm);
			return beneficio = salario * funcionario.getCargo().getBeneficio();

		}
		return beneficio;

	}

	private Double getBeneficioVendedor(String nomeFuncionario, FuncionarioForm funcionarioForm) {

		Double salarioComBeneficio = 0.0;
		Venda venda = vendaRepository.findVendaByNomeVendedorAndPeriodoVenda(nomeFuncionario,
				funcionarioForm.getPeriodo());
		salarioComBeneficio = venda.getValorVenda();

		return salarioComBeneficio;

	}

	private List<Funcionario> buscarOuFalhar(String nome) {
		List<Funcionario> funcionarios;
		funcionarios = funcionarioRepository.findFuncionarioByNome(nome);
		if (funcionarios == null || funcionarios.isEmpty()) {
			throw new FuncionarioNaoEncontradoException(nome);
		}
		return funcionarios;
	}

}
