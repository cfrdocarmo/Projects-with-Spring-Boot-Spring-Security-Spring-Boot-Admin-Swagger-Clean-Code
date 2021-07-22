package br.com.alura.loja.orcamento.situacao;

import br.com.alura.loja.orcamento.Orcamento;

public class Reprovado extends SituacaoOrcamento{

	public void finalizado(Orcamento orcamento) {
		orcamento.setSituacao(new Finalizado());
	}
	
}
