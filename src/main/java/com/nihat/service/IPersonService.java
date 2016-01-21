package com.nihat.service;

import java.util.List;

import com.nihat.model.Person;
// Person Service Interface
public interface IPersonService {

	public Person addPerson(Person person);

	public List<Person> listPerson();

	public void deletePerson(String id);

	public Person updatePerson(String id, String name, String surname, String telNumber);

	public Person findById(String id);

}
