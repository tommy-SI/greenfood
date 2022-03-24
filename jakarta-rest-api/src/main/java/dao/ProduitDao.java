package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entities.Produit;
import exceptions.technical.DAOException;
import jpa.JpaEntityManager;

@Stateless
public class ProduitDao {

	private static EntityManager entityManager = JpaEntityManager.getEntityManager();

	public Produit get(long id) throws DAOException {
		try {
			return entityManager.find(Produit.class, id);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public Produit getByName(String name) throws DAOException {
		try {
			Query query = entityManager.createQuery("SELECT p FROM Produit p WHERE p.nom=:ma_var_name");
			query.setParameter("ma_var_name", name);
			return (Produit) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<Produit> getAll() throws DAOException {
		try {
			return entityManager.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void create(Produit entity) throws DAOException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void update(Produit entity) throws DAOException {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void delete(Produit entity) throws DAOException {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
