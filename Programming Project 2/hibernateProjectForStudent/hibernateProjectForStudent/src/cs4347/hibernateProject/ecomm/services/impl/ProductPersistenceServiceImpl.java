package cs4347.hibernateProject.ecomm.services.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cs4347.hibernateProject.ecomm.entity.Product;
import cs4347.hibernateProject.ecomm.services.ProductPersistenceService;
import cs4347.hibernateProject.ecomm.util.DAOException;

public class ProductPersistenceServiceImpl implements ProductPersistenceService
{
	@PersistenceContext 
	public EntityManager em; 
		
	@Override
	public void create(Product product) throws SQLException, DAOException
	{
		try {
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public Product retrieve(Long id) throws SQLException, DAOException
	{
		try {
			em.getTransaction().begin();
			Product prod = em.find(Product.class, id);
			em.getTransaction().commit();
			return prod;
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(Product product) throws SQLException, DAOException
	{
		try {
			em.getTransaction().begin();
			Product p = em.find(Product.class, product.getId());
			p.setProdName(product.getProdName());
			p.setProdDescription(product.getProdDescription());
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Long id) throws SQLException, DAOException
	{
		try {
			em.getTransaction().begin();
			Product p = (Product)em.find(Product.class, id);
			em.remove(p);
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
			
			//put in an error check, something like line 83 in sample (DepartmentServiceImpl)
	}
	}

	@Override
	public Product retrieveByUPC(String upc) throws SQLException, DAOException
	{
		em.getTransaction().begin();
		Product prod = (Product)em.createQuery("from Product as p where p.prodUPC = :prodUPC")
				.setParameter("prodUPC", upc)
				.getResultList();
		em.getTransaction().commit();
		return prod;
	}

	@Override
	public List<Product> retrieveByCategory(int category) throws SQLException, DAOException
	{
		em.getTransaction().begin();
		List<Product> prod = (List<Product>)em.createQuery("from Product as p where p.prodCategory = :prodCat")
				.setParameter("prodCat", category)
				.getResultList();
		em.getTransaction().commit();
		return prod;
	}

}

