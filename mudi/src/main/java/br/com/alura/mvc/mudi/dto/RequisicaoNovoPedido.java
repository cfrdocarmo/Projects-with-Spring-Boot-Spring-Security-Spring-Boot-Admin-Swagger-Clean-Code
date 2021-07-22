package br.com.alura.mvc.mudi.dto;

import javax.validation.constraints.NotBlank;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

public class RequisicaoNovoPedido {

	@NotBlank()
	private String nomeDoProduto;
	
	@NotBlank
	private String urlDoProduto;
	
	@NotBlank
	private String utlImagem;
	
	private String descricao;

	public String getNomeDoProduto() {
		return nomeDoProduto;
	}

	public void setNomeDoProduto(String nomeDoProduto) {
		this.nomeDoProduto = nomeDoProduto;
	}

	public String getUrlDoProduto() {
		return urlDoProduto;
	}

	public void setUrlDoProduto(String urlDoProduto) {
		this.urlDoProduto = urlDoProduto;
	}

	public String getUtlImagem() {
		return utlImagem;
	}

	public void setUtlImagem(String utlImagem) {
		this.utlImagem = utlImagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setDescricao(descricao);
		pedido.setNomeDoProduto(nomeDoProduto);
		pedido.setUrlDoProduto(urlDoProduto);
		pedido.setUrlImagem(utlImagem);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		return pedido;
	}

}
