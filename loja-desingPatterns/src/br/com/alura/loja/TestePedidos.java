package br.com.alura.loja;

import java.math.BigDecimal;
import java.util.Arrays;

import br.com.alura.loja.pedido.GeraPedido;
import br.com.alura.loja.pedido.GeraPedidoHandler;
import br.com.alura.loja.pedido.acao.EnviarEmailPedido;
import br.com.alura.loja.pedido.acao.LogDePedido;
import br.com.alura.loja.pedido.acao.SalvarPedidoBancoDeDados;

public class TestePedidos {

	public static void main(String[] args) {

		String cliente = "Carlos Felipe";
		BigDecimal valorOrcametno = new BigDecimal("745.99");
		int quantidadeItens = 3;
		
		GeraPedido gerador = new GeraPedido(cliente, valorOrcametno, quantidadeItens);
		GeraPedidoHandler handler = new GeraPedidoHandler(
				Arrays.asList(
				new SalvarPedidoBancoDeDados(),
				new EnviarEmailPedido(),
				new LogDePedido()
				));
		handler.execute(gerador);
	}

}
