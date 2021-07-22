package br.com.alura.loja.orcamento;

import java.util.Map;

import br.com.alura.loja.http.HttpAdapter;
import br.com.alura.loja.http.JavaHttpClient;

public class RegistroOrcamento {
	
	private HttpAdapter http;
	
	public RegistroOrcamento(JavaHttpClient javaHttpClient) {
	}

	public void RegistroOrcamento(Orcamento orcamento) {
		this.http = http;
	}
	
	public void registrar(Orcamento orcamento) {
	
		if (!orcamento.isFinalizado()) {
			throw new DomainException("Orçamento não finalizado");
		}
		
		String url = "http://api.externa/orcamento";
		Map<String, Object> dados= Map.of(
				"valor", orcamento.getValor(),
				"quantidadeItens", orcamento.getQuantidadeItens()
				);
		http.post(url, dados);
	}
	
}
