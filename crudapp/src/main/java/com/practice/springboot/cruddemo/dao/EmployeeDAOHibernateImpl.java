package com.practice.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	//define field for entity
	private EntityManager entityManager;
	
	//setup constructor injection
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		//get hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
		
		//create query
		Query<Employee> theQuery=currentSession.createQuery("from Employee", Employee.class);
		
		//execute query and get result list
		List<Employee> employees=theQuery.getResultList();
		
		//return result
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get current hib session
		Session currentSession=entityManager.unwrap(Session.class);
		
		//get employees
		Employee theEmployee=currentSession.get(Employee.class, theId);
		
		//return employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {

		//get current hib session
		Session currentSession=entityManager.unwrap(Session.class);
		
		//save employee
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	public void deleteById(int theId) {

		//get hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
				
		//create query
		Query theQuery=currentSession.createQuery("delete from Employee where id=:employeeId");
				
		//execute query and get result list
		theQuery.setParameter("employeeId", theId);
				
		//return result
		theQuery.executeUpdate();
	}

	

}
