package service;

import entity.ContactBookEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import app.HibernateUtil;
import java.util.List;
import java.util.Collections;
import jakarta.persistence.TypedQuery;
import java.util.*;

public class ContactBookService 
{
	
	private final EntityManagerFactory entitymanager;
	
	public ContactBookService()
	{
        this.entitymanager = HibernateUtil.getEntityManagerFactory();
    }
	
	public void CreateNewContact(String name, long phone, String Email)
	{
	    EntityTransaction transaction = null;
	    EntityManager em = entitymanager.createEntityManager();
	    try
	    {
	        transaction = em.getTransaction();
	        transaction.begin();
	        ContactBookEntity cbe = new ContactBookEntity();
	        cbe.setname(name);
	        cbe.setphone(phone);
	        cbe.setEmail(Email);
	        em.persist(cbe);
	        transaction.commit();
	        System.out.println("Contact added Successfully..!!");
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        em.close();
	    }
	}

	
	public List<ContactBookEntity> findAllContacts()
	{
	    EntityManager em = entitymanager.createEntityManager();
	    try 
	    {
	        TypedQuery<ContactBookEntity> query = em.createQuery("FROM ContactBookEntity", ContactBookEntity.class);
	        return query.getResultList();
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        return Collections.emptyList(); 
	    } 
	    finally 
	    {
	        em.close(); 
	    }
	}

	
	public void updateContact(int contactid, String name, long phone, String Email)
	{
	    EntityTransaction transaction = null;
	    EntityManager em = entitymanager.createEntityManager();
	    try
	    {
	        transaction = em.getTransaction();
	        transaction.begin();
	        ContactBookEntity cbe = em.find(ContactBookEntity.class, contactid);
	        if (cbe != null) 
	        {
	            cbe.setname(name);       
	            cbe.setphone(phone);     
	            cbe.setEmail(Email);     
	            System.out.println("Contact Updated Successfully.!!");
	        } 
	        else 
	        {
	            System.out.println("Contact ID not found.!!");
	        }
	        transaction.commit(); 
	    } 
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	    } finally {
	        em.close(); 
	    }
	}
public void DeleteContact(int contactid)
	{
		EntityTransaction transaction=null;
		EntityManager em=entitymanager.createEntityManager();
		try
		{
			transaction=em.getTransaction();
			transaction.begin();
			ContactBookEntity cbe=em.find(ContactBookEntity.class,contactid);
			if(cbe!=null)
			{
				em.remove(cbe);
				transaction.commit();
				System.out.println("Contact Deleted Successfully..!!");
				
			}
			else
			{
				System.out.println("Contact Not Found");
				transaction.rollback();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void ShutDown()
	{
		HibernateUtil.shutDown();
	}
}
