package br.com.alura.rh.service.promocao;

import br.com.alura.rh.ValidacaoException;
import br.com.alura.rh.model.Cargo;
import br.com.alura.rh.model.Funcionario;

public class PromocaoService {

	public void promover(Funcionario funcionario, boolean metaBatida) {
		Cargo cargoAtual = funcionario.getDadosPessoais().getCargo();
		if (Cargo.GERENTE == cargoAtual || !metaBatida) {
			throw new ValidacaoException("Ou é um Gerentes não podem ser promovidos ou não bateu a meta");
		}
		
		Cargo novoCargo = cargoAtual.getProximoCargo();
		funcionario.promover(novoCargo);
		
	}
}
