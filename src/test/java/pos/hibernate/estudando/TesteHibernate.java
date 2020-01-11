package pos.hibernate.estudando;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Test;

import dao.DaoGenerico;
import model.Usuario;

public class TesteHibernate {
	
	@Test
	public void testeConexao() {
		
		HibernateUtil.getEntityManager();
		
	}
	
	@Test
	public void testeCadastro() {
		
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		for(int i = 1; i <= 50; i++) {
			Usuario usuario = new Usuario();
			usuario.setSenha("secret"+i);
			usuario.setEmail(i+"antonio@santos"+i+".com.br");
			usuario.setLogin(i+"antonio");
			usuario.setNome("Antonio "+i+" dos Santos");
			
			dao.salvar(usuario);
			
			System.out.println( i+"º Cadastro com Sucesso!.");
		}
		
	}
	
	@Test
	public void testePesquisa() {
		
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
						
		Usuario usuario = new Usuario();
		usuario.setId(52L);
		
		usuario = dao.pesquisar(usuario);
		
		System.out.println(usuario);
	}
	
	@Test
	public void testeConsultarTodos() {
		
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		
		List<Usuario> lista = dao.pesquisarTodos(Usuario.class);
		
		for (Usuario usuario : lista) {
			System.out.println(usuario);
			System.out.println("---------------------------------");
		}
	}
	
	@Test
	public void testePesquisaPorId() {
		
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
						
		Usuario usuario = dao.pesquisarPorId(Usuario.class, 1L);
		
		System.out.println(usuario);
	}
	
	@Test 
	public void testeAtualizacao() {
		
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		
		Usuario usuario = dao.pesquisarPorId(Usuario.class, 1L);
		
		usuario.setNome("Tikinho doido ");
		usuario.setEmail("tikinho@doido.com.br");
		usuario.setLogin("tikinho");
		usuario.setSenha("123456");
		
		usuario = dao.atualizar(usuario);
		
		System.out.println(usuario);
		
	}
	
	@Test 
	public void testeExclusao() {
		
		DaoGenerico<Usuario> dao =  new DaoGenerico<Usuario>();
		
		Usuario usuario = dao.pesquisarPorId(Usuario.class, 51L);
		
		dao.excluir(usuario);
		
		System.out.println("Exclusão com sucesso");
			
	}
	

	@Test
	public void testeCriptografia() {
		
	       String s="Texto de Exemplo";
	       MessageDigest m;
		try {
			
			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(),0,s.length());
		    System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
		    
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	   
	}
	
}
