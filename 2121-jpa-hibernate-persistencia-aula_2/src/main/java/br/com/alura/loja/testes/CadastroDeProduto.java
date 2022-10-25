package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		//instanciando categoria de celulares
		//estados: new/trasient/persist/managed/detached/bd
		
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		//a entidade(jpa) passa a ser observada pelo banco de dados: managed ou gerenciada...
		em.persist(celulares);
		
		//update da entidade/sincronizar no banco de dados
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
	
		produtoDao.cadastrar(celular);
		
		//a entidade commit faz o insert no banco de dados
		
		em.getTransaction().commit();
		em.close();
		//estado detached: é ignorado,(close), após ser gerenciado... 
		celulares.setNome("123");
	}

}
