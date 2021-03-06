package br.com.alura.forum.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.forum.modelo.Curso;
import junit.framework.Assert;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void deveriaCarreagarUmCursoAoBuscarPeloSeuNome() {
		String nomeCurso = "HTML 5";
		
		Curso html5 = new Curso();
		html5.setNome(nomeCurso);
		html5.setCategoria("Programação");
		em.persist(html5);
		
		Curso curso = cursoRepository.findByNome(nomeCurso);
		Assert.assertNotNull(curso);
		Assert.assertEquals(nomeCurso, curso.getNome());
	}

	
	@Test
	public void naoDeveriaCarreagarUmCursoAoBuscarPeloSeuNomeQueNaoExiste() {
		String nomeCurso = "JPa";
		Curso curso = cursoRepository.findByNome(nomeCurso);
		Assert.assertNull(curso);
	}
	
}
