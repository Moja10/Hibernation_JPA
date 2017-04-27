package cs4347.hibernateProject.ecomm.services.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cs4347.hibernateProject.ecomm.entity.Customer;
import cs4347.hibernateProject.ecomm.entity.Purchase;
import cs4347.hibernateProject.ecomm.services.CustomerPersistenceService;
import cs4347.hibernateProject.ecomm.util.DAOException;

public class CustomerPersistenceServiceImpl implements CustomerPersistenceService
{
	@PersistenceContext 
	public EntityManager em; 
		
	@Override
	public void create(Customer customer) throws SQLException, DAOException
	{
		try {
			em.getTransaction().begin();
			em.persist(customer);
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public Customer retrieve(Long id) 
	{
		try {
			em.getTransaction().begin();
			Customer cust= (Customer)em.find(Customer.class, id);
			em.getTransaction().commit();
			return cust;
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(Customer c1) throws SQLException, DAOException
	{
	}

	@Override
	public void delete(Long id) throws SQLException, DAOException
	{
	}

	@Override
	public List<Customer> retrieveByZipCode(String zipCode) throws SQLException, DAOException
	{
//		if((Integer.parseInt(zipCode) < 0 || Integer.parseInt(zipCode) > 99999)){
//			throw new DAOException("Invalid product ID provided");
//		}
		List<Customer> custs = (List<Customer>)em.createQuery("from Customer as c where c.address.zipcode = :zip")
		.setParameter("zip", zipCode)
		.getResultList();

		return custs;
	}

	@Override
	public List<Customer> retrieveByDOB(Date startDate, Date endDate) throws SQLException, DAOException
	{
		
		List<Customer> custs = (List<Customer>)em.createQuery("from Customer as c where c.dob between :start and :end")
				.setParameter("start", startDate)
				.setParameter("end", endDate)
				.getResultList();

		return custs;
	}
}
