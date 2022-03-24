package business;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dao.ProduitDao;
import entities.Produit;
import exceptions.technical.DAOException;

@Singleton
public class ProduitBusiness {

	@Inject
	private ProduitDao produitDao;

	public List<Produit> getAllProduits() throws DAOException {
		return produitDao.getAll();
	}

	public Produit get(int id) throws DAOException {
		return produitDao.get(id);
	}

	public Produit add(Produit produit) throws DAOException {
		produitDao.create(produit);
		return produit;
	}

	public void delete(Produit produit) throws DAOException {
		produitDao.delete(produit);
	}

	public Produit update(Produit produit) throws DAOException {
		produitDao.update(produit);
		return produit;
	}

	public Produit search(String name) throws DAOException {
		return produitDao.getByName(name);
	}

}
