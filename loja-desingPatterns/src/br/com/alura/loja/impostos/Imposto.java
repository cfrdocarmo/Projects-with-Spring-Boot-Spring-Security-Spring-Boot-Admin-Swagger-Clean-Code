package br.com.alura.loja.impostos;

import java.math.BigDecimal;

import br.com.alura.loja.orcamento.Orcamento;

public abstract class Imposto {
	
	private Imposto outro;
	
	public Imposto(Imposto outro) {
		this.outro = outro;
	}

	protected abstract BigDecimal realizarCalculo(Orcamento orcamento);

	public BigDecimal calcular(Orcamento orcametno) {
		BigDecimal valorImposto = realizarCalculo(orcametno);
		BigDecimal valorDoOutroImposto = BigDecimal.ZERO;
		if (outro != null) {
			valorDoOutroImposto = outro.realizarCalculo(orcametno);
		}
		
		return valorImposto.add(valorDoOutroImposto);
		
	}
}
