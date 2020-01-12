package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pos.hibernate.estudando.HibernateUtil;

public class DaoGenerico<E> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();

	public void salvar(E entidade) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();

	}

	public E atualizar(E entidade) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E resultado = entityManager.merge(entidade);
		transaction.commit();

		return resultado;
	}

	public void excluir(E entidade) {

		Object id = HibernateUtil.getPrimaryKey(entidade);

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.createNativeQuery("DELETE FROM " + entidade.getClass().getSimpleName() + " WHERE id = " + id)
				.executeUpdate();

		transaction.commit();

	}

	public List<E> pesquisarTodos(Class<E> entidade) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<E> lista = entityManager.createQuery("FROM " + entidade.getName()).getResultList();

		transaction.commit();

		return lista;

	}

	public E pesquisar(E entidade) {

		Object id = HibernateUtil.getPrimaryKey(entidade);
		E entidadeEncontrada = (E) entityManager.find(entidade.getClass(), id);

		return entidadeEncontrada;
	}

	public E pesquisarPorId(Class<E> entidade, Long id) {

		E e = entityManager.find(entidade, id);

		return e;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
