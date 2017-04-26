package cs4347.hibernateProject.ecomm.services.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import basicJPA.entity.Department;
import cs4347.hibernateProject.ecomm.entity.Purchase;
import cs4347.hibernateProject.ecomm.services.PurchasePersistenceService;
import cs4347.hibernateProject.ecomm.services.PurchaseSummary;
import cs4347.hibernateProject.ecomm.util.DAOException;

public class PurchasePersistenceServiceImpl implements PurchasePersistenceService
{
	@PersistenceContext 
	public EntityManager em; 
		
	@Override
	public void create(Purchase purchase) throws SQLException, DAOException
	{
		try {
			em.getTransaction().begin();
			em.persist(purchase);
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public Purchase retrieve(Long id) throws SQLException, DAOException
	{
		try {
			em.getTransaction().begin();
			Purchase purch= em.find(Purchase.class, id);
			em.getTransaction().commit();
			return purch;
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(Purchase purchase) throws SQLException, DAOException
	{
		try {
			em.getTransaction().begin();
			Purchase purch = em.find(Purchase.class, purchase.getId());
			purch.setPurchaseAmount(purchase.getPurchaseAmount());
			purch.setPurchaseDate(purchase.getPurchaseDate());
			purch.setCustomer(purchase.getCustomer());
			purch.setProduct(purchase.getProduct());
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
		em.getTransaction().begin();
		Purchase purch = (Purchase)em.createQuery("from Purchase as p where p.id = :id")
				.setParameter("id", id)
				.getSingleResult();
		
		if(purch == null) {
			em.getTransaction().rollback();
			throw new DAOException("Purchase ID Not Found " + id);
		}
		
		em.remove(purch); // Does this work?
		em.getTransaction().commit();
	}

	@Override
	public List<Purchase> retrieveForCustomerID(Long customerID) throws SQLException, DAOException
	{
		if((customerID < 0)){
			throw new DAOException("Invalid customer ID provided");
		}
		List<Purchase> listofpurch = new ArrayList<Purchase>();
		ResultSet rs = (ResultSet) em.createQuery("from Purchase as p where p.customerID = :custID")
				.setParameter("custID", customerID)
				.getResultList();
		if(!rs.next()){
				return null;
		}
		while(rs.next()){
			// Create a new Purchase object
			Purchase purch = new Purchase();
			// Fill Purchase object with values from ResultSet
			purch.setId(rs.getLong("ID"));
			//purch.setProduct(rs.getLong("productID"));
			//purch.setCustomer(rs.getLong("customerID"));
			purch.setPurchaseAmount(rs.getDouble("purchaseAmount"));
			purch.setPurchaseDate(rs.getDate("purchaseDate"));
			// Add purch object to result arraylist
			listofpurch.add(purch);
		}
		return listofpurch;
	}

	@Override
	public List<Purchase> retrieveForProductID(Long productID) throws SQLException, DAOException
	{
		return null;
	}

	@Override
	public PurchaseSummary retrievePurchaseSummary(Long customerID) throws SQLException, DAOException
	{
		return null;
	}
}