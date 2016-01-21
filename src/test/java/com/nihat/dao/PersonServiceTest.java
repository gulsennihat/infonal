package com.nihat.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.verification.Times;

import com.nihat.model.Person;
import com.nihat.service.PersonServiceImpl;


public class PersonServiceTest {
	
	@Mock
	private IPersonDAO personDAO;
	
	@InjectMocks
	private PersonServiceImpl service;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
 


	@Test
	public void addPersonTest() throws Exception {
		final Person person= new Person();
		  person.setId("1");
		  person.setName("Nihat");
		  person.setSurname("Gülşen");
		  person.setTelNumber("123456");  
		
	        Mockito.when(service.addPerson(person)).thenReturn(person);	        
	        final Person result = service.addPerson(person);        
	        Assert.assertEquals(person, result);
	        Mockito.verify(personDAO, new Times(1)).addPerson(Mockito.any(Person.class));
      
	}

	@Test
	public void personListTes() throws Exception {
		final Person person1= new Person();
		  person1.setId("1");
		  person1.setName("Nihat");
		  person1.setSurname("Gülşen");
		  person1.setTelNumber("123456"); 
		  
		  final Person person2= new Person();
		  person2.setId("2");
		  person2.setName("Infonal");
		  person2.setSurname("test");
		  person2.setTelNumber("654321"); 
		  
		  List<Person> persons = new ArrayList<Person>();
		  persons.add(person1);
		  persons.add(person2);
		  
		  Mockito.when(service.listPerson()).thenReturn(persons);
		  final List<Person> result = service.listPerson();     
	        Assert.assertEquals(persons, result);
	        Mockito.verify(personDAO, new Times(1)).listPerson();
	}

}
