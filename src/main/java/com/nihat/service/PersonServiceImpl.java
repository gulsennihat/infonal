package com.nihat.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nihat.dao.IPersonDAO;
import com.nihat.model.Person;

@Service
public class PersonServiceImpl implements IPersonService {

	// log4j implementation
	private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);

	@Autowired // Injection PersonDAO Interface
	private IPersonDAO personDAO;

	public IPersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(IPersonDAO personDAO) {
		this.personDAO = personDAO;
		logger.info("person service worked");
	}
	//Service crud process methods 
	@Transactional
	@Override
	public Person addPerson(Person person) {
		return this.personDAO.addPerson(person);
	}

	@Transactional
	@Override
	public List<Person> listPerson() {
		return this.personDAO.listPerson();
	}

	@Transactional
	@Override
	public void deletePerson(String id) {

		this.personDAO.deletePerson(id);
	}

	@Transactional
	@Override
	public Person updatePerson(String id, String name, String surname, String telNumber) {

		return this.personDAO.updatePerson(id, name, surname, telNumber);
	}

	@Override
	public Person findById(String id) {

		return this.personDAO.findById(id);
	}

}
