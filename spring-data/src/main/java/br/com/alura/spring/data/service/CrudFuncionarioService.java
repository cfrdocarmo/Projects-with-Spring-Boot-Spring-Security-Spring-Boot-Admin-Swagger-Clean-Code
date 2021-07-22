package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeDeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
			CargoRepository cargoRepository, UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
		this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
	}
	
public void inicial(Scanner scanner) {
		
		while(system) {
			System.out.println("Qual ação de funcionário deseja executar: ");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar(scanner);
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
			
		}
		
	}


	private void salvar(Scanner scanner) {
		
		System.out.println("Digite o nome do funcionário: ");
		clearBuffer(scanner);
		String nome = scanner.nextLine();
		

		System.out.println("Digite o CPF do funcionário: ");
		String cpf = scanner.nextLine();

		System.out.println("Digite o salário do funcionário: ");
		BigDecimal salario = scanner.nextBigDecimal();
		clearBuffer(scanner);
		
		System.out.println("Digite a data da Contratação: ");
		String dataContratacao = scanner.nextLine();

		System.out.println("Digite o Id (Inteiro) do cargo do funcionário: ");
		Integer cargoId = scanner.nextInt();
		clearBuffer(scanner);

		List<UnidadeDeTrabalho> unidades = unidade(scanner);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(funcionario.getId());
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		funcionario.setDataContratacao(funcionario.getDataContratacao());
		
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadeDeTrabalho(unidades);
		
		funcionarioRepository.save(funcionario);
		System.out.println("Salvo");
		
	}

	private List<UnidadeDeTrabalho> unidade(Scanner scanner){
		Boolean isTrue = true;
		List<UnidadeDeTrabalho> unidades = new ArrayList<>();
		
		while (isTrue) {
			System.out.println("Digite a unidadeId (Para sair digite 0) ");
			Integer unidadeId = scanner.nextInt();
			
			if (unidadeId != 0) {
				Optional<UnidadeDeTrabalho> unidade = unidadeDeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}
		
		return unidades;
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o Id");
		Integer id = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Digite o nome");
		String nome = scanner.nextLine();
		scanner.nextLine();
		
		System.out.println("Digite o cpf");
		String cpf = scanner.nextLine();
		scanner.nextLine();
		
		System.out.println("Digite o salario");
		BigDecimal salario = scanner.nextBigDecimal();
		scanner.nextLine();
		
		System.out.println("Digite a data da contratação");
		String dataContratacao = scanner.nextLine();
		scanner.nextLine();
		
		System.out.println("Digite o cargoId");
		Integer cargoId = scanner.nextInt();
		scanner.nextLine();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		
		funcionarioRepository.save(funcionario);
		System.out.println("Alterado");
	}


	private void visualizar(Scanner scanner) {
		System.out.println("Qual página você deseja visualizar: ");
		clearBuffer(scanner);
		Integer page = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(page, 3, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Página atual " + funcionarios.getNumber());
		System.out.println("Total elementos " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o Id para Deletar");
		Integer id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}


	  private static void clearBuffer(Scanner scanner) {
	        if (scanner.hasNextLine()) {
	            scanner.nextLine();
	        }
	    }





	
}
