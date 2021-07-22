package br.com.alura.forum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;
import junit.framework.Assert;

@SpringBootTest
class ForumApplicationTests {

	@Test
	void contextLoads() {
		Assert.assertTrue(true);
	}


}
