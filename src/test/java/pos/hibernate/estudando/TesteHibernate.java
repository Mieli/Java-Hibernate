package pos.hibernate.estudando;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import dao.DaoGenerico;
import model.Telefone;
import model.Usuario;

public class TesteHibernate {

	@Test
	public void testeConexao() {

		HibernateUtil.getEntityManager();

	}

	@Test
	public void testeCadastro() {

		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		for (int i = 1; i <= 50; i++) {
			Usuario usuario = new Usuario();
			usuario.setSenha("secret" + i);
			usuario.setEmail(i + "antonio@santos" + i + ".com.br");
			usuario.setLogin(i + "antonio");
			usuario.setNome("Antonio " + i + " dos Santos");

			dao.salvar(usuario);

			System.out.println(i + "º Cadastro com Sucesso!.");
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

		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();

		Usuario usuario = dao.pesquisarPorId(Usuario.class, 51L);

		dao.excluir(usuario);

		System.out.println("Exclusão com sucesso");

	}

	@Test
	public void testeCriptografia() {

		String s = "Texto de Exemplo";
		MessageDigest m;
		try {

			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(), 0, s.length());
			System.out.println("MD5: " + new BigInteger(1, m.digest()).toString(16));

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testeQueryString() {
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();

		List<Usuario> lista = dao.getEntityManager().createQuery(" FROM Usuario")
													.getResultList();

		for (Usuario usuario : lista) {
			System.out.println(usuario);
		}
	}

	@Test
	public void testeQueryPorNome() {

		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		List<Usuario> lista = dao.getEntityManager().createQuery(" FROM Usuario WHERE nome = 'Tikinho doido'")
													.getResultList();

		for (Usuario usuario : lista) {
			System.out.println(usuario);
		}
	}

	@Test
	public void testeQueryMaximoPorOrdenacao() {

		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		List<Usuario> lista = dao.getEntityManager().createQuery(" FROM Usuario ORDER BY id")
													.setMaxResults(10)
													.getResultList();

		for (Usuario usuario : lista) {
			System.out.println(usuario);
		}
	}
	
	@Test
	public void testeQueryComParametros() {
		
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		
		List<Usuario> lista = dao.getEntityManager().createQuery(" FROM Usuario WHERE nome = :nome")
													.setParameter("nome", "Tikinho doido")
													.getResultList();
		
		for (Usuario usuario : lista) {
			System.out.println(usuario);
		}		
	}
	
	
	@Test
	public void testeQuerySomarTodosId() {
		
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		
		Long somaDosIds = (Long) dao.getEntityManager().createQuery("SELECT sum(u.id) FROM Usuario u").getSingleResult();
		
		System.out.println(" Soma => "+somaDosIds);		
	}
	
	@Test
	public void testeQueryMediaTodosId() {
		
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		
		Double mediaDosIds = (Double) dao.getEntityManager().createQuery("SELECT avg(u.id) FROM Usuario u").getSingleResult();
		
		System.out.println(" Média => "+mediaDosIds);		
	}
	
	
	
	@Test
	public void testeQueryNamedBuscarTodos() {
		
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		List<Usuario> lista = dao.getEntityManager().createNamedQuery("Usuario.findAll").getResultList();
		
		for (Usuario usuario : lista) {
			System.out.println(usuario);
		}	
	}
	

	@Test
	public void testeQueryNamedBuscarPorNome() {
		
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		List<Usuario> lista = dao.getEntityManager().createNamedQuery("Usuario.findByName")
													.setParameter("nome", "Antonio 3 dos Santos")
													.getResultList();
													
		for (Usuario usuario : lista) {
			System.out.println(usuario);
		}	
	}
	
	@Test
	public void testeCadastroTelefoneUsuario() {
		
		Random random = new Random();
		DaoGenerico dao = new DaoGenerico();
		
		for(int i = 1; i <= 49; i++) {
		
			String numero = "(" + (random.nextInt(100)+i) +") 9"+ random.nextInt(1000) +"-"+ random.nextInt(1000);
			Integer id_usuario = random.nextInt(10);
			
			Usuario usuario = (Usuario) dao.pesquisarPorId(Usuario.class, id_usuario.longValue());
			
			Telefone fone = new Telefone();
			fone.setNumero(numero);
			
			if(i%2 == 0)
				fone.setTipo("Celular");
			else
				fone.setTipo("Residencial");
			fone.setUsuario(usuario);
			
			dao.salvar(fone);
			
			System.out.println("Telefone cadastrado com Sucesso");
		}		
	}
	
	@Test
	public void testePesquisarTelefonePorUsuario() {
		DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
		
		Usuario usuario = dao.pesquisarPorId(Usuario.class, 6L);
		
		for (Telefone telefone : usuario.getTelefones()) {
			
			System.out.println(telefone);
			
		}
		
		
	}


}
