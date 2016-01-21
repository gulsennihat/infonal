package com.nihat.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nihat.model.Person;
import com.nihat.service.PersonServiceImpl;

//controller class
@Controller
public class PersonController {

	// log4j
	private static final Logger logger = Logger.getLogger(PersonController.class);

	@Autowired
	private PersonServiceImpl personServiceImpl;

	// Controller for getting list of user and show in index page
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getPersonList(ModelMap model) {

		// DOMConfigurator.configure("resources/log4j.xml");
		model.addAttribute("personList", personServiceImpl.listPerson());
		logger.info("personList returned");
		return "index";
	}

	// Controller for saving user
	@RequestMapping(value = "/person/save", method = RequestMethod.POST)
	@ResponseBody
	public Person createPerson(@ModelAttribute Person person) {
		personServiceImpl.addPerson(person);
		logger.info("person saved");
		return person;
	}

	// Controller for delete user
	@RequestMapping(value = "/person/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deletePerson(@PathVariable String id) {
		personServiceImpl.deletePerson(id);
		logger.info("person deleted");
	}

	// Controller for update user
	@RequestMapping(value = "/person/update", method = RequestMethod.POST)
	@ResponseBody
	public Person updatePerson(HttpServletRequest request) {
		Person person = personServiceImpl.updatePerson(request.getParameter("id"), request.getParameter("name"),
				request.getParameter("surname"), request.getParameter("telNumber"));
		logger.info("person updated");
		return person;
	}

}
