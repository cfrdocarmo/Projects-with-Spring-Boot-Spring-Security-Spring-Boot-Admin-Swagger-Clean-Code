package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository; 
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
			
			while(system) {
				System.out.println("Qual ação de relatório deseja executar: ");
				System.out.println("0 - Sair");
				System.out.println("1 - Buscar funcionario pelo nome");
				System.out.println("2 - Busca funcionário nome, salario maior e data contratação");
				System.out.println("3 - Busca funcionário por data de contratação");
				System.out.println("4 - Pesquisa funcionário salário");

				int action = scanner.nextInt();
				
				switch (action) {
				case 1: buscaFuncionarioNome(scanner);
					break;
				case 2: findByNomeSalarioMaiorDataContratacao(scanner);
				break;
				case 3:  buscaFuncionarioDataContratacao(scanner);
				break;
				case 4: pesquisaFuncionarioSalario();
				break;
				default:
					system = false;
					break;
				}
				
			}
			
		}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar: ");
		clearBuffer(scanner);
		String nome = scanner.nextLine();
		
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		
		list.forEach(System.out::println);
	}
	
	private void findByNomeSalarioMaiorDataContratacao(Scanner scanner) {
		System.out.println("Qual o nome do funcionário deseja buscar: ");
		clearBuffer(scanner);
		String nome = scanner.nextLine();

		System.out.println("Qual o salário do funcionário deseja buscar: ");
		//clearBuffer(scanner);
		BigDecimal salario = scanner.nextBigDecimal();
		
		
		System.out.println("Qual a data de Contratação do funcionário deseja buscar: ");
		clearBuffer(scanner);
		String dataContratacao = scanner.nextLine();
		LocalDate localDate = LocalDate.parse(dataContratacao, formatter);
		
		
		List<Funcionario> list = funcionarioRepository.findByNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Qual a data de contratação deseja buscar");
		clearBuffer(scanner);
		String dataContratacao = scanner.nextLine();
		LocalDate localDate = LocalDate.parse(dataContratacao, formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
		
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id " + f.getId() + " | nome: " + f.getNome() + " | salario" + f.getSalario()));
	}
	
	private static void clearBuffer(Scanner scanner) {
	        if (scanner.hasNextLine()) {
	            scanner.nextLine();
	        }
	    }
	
}
