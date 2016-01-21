package com.nihat.controller;

import com.nihat.dao.IPersonDAO;
import com.nihat.model.Person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"resources/servlet-context.xml"})
@WebAppConfiguration
public class PersonControllerTest {
	  private MockMvc mockMvc;

	    @Mock
	    private IPersonDAO personService;

	    @InjectMocks
	    private PersonController personController;

	    @Before
	    public void setUp() {
	    MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
	    }

	    @Test
	    public void testExample() throws Exception{
	        Person person = new Person();
	        person.setId("1");
	        person.setName("Nihat");
	        person.setSurname("Gülşen");
	        person.setTelNumber("123456");

	     
	        when(personService.addPerson(any(Person.class))).thenReturn(person);
        
	        mockMvc.perform(post("/person/save"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$[0].id", is(1)))
	                .andExpect(jsonPath("$[0].name", is("Nihat")))
	                .andExpect(jsonPath("$[0].surname", is("Gülşen")))
	                .andExpect(jsonPath("$[0].telNumber", is("123456")));

	 
	        verify(personService, times(1)).addPerson(person);
	        verifyNoMoreInteractions(personService);
	    }
}
