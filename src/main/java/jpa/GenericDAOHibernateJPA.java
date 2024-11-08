package jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.GenericDao;
import utils.EMF;

public abstract class GenericDAOHibernateJPA<T> implements GenericDao<T> {
	protected Class<T> persistentClass;

	public GenericDAOHibernateJPA(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public T create(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(entity);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e; // escribir en un log o mostrar un mensaje
		} finally {
			em.close();
		}
		return entity;
	}

	@Override
	public T getById(Serializable id) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		T entity = null;
		try {
			entity = em.find(this.getPersistentClass(), id);
		} catch (RuntimeException e) {
			throw e; // escribir en un log o mostrar un mensaje
		} finally {
			em.close();
		}
		return entity;
	}

	@Override
	public List<T> getAll(String columnOrder) {
		Query consulta = EMF.getEMF().createEntityManager()
				.createQuery("select e from " + getPersistentClass().getSimpleName() + " e order by e." + columnOrder);
		List<T> resultado = consulta.getResultList();
		return resultado;
	}

	@Override
	public T update(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		T entityMerged = em.merge(entity);
		etx.commit();
		em.close();
		return entityMerged;
	}

	@Override
	public boolean exists(Serializable id) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		T entity = null;
		try {
			entity = em.find(this.getPersistentClass(), id);
		} catch (RuntimeException e) {
			throw e; // escribir en un log o mostrar un mensaje
		} finally {
			em.close();
		}
		return entity != null ? true : false;
	}

	@Override
	public void delete(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(em.merge(entity));
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e; // escribir en un log o mostrar un mensaje
		} finally {
			em.close();
		}
	}

	@Override
	public T delete(Serializable id) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		T entity = null;

		try {
			tx = em.getTransaction();
			tx.begin();
			entity = em.find(this.getPersistentClass(), id);
			if (entity != null) {
				em.remove(entity);
			}
			tx.commit();
		} catch (RuntimeException e) {
			// Si ocurre un error, hacer rollback para revertir la transacción
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			// Re-lanzar la excepción o manejarla adecuadamente (ejemplo: loguearla)
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return entity; // Devolver la entidad eliminada o null si no fue encontrada
	}

}
