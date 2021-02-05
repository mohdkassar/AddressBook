package com.address.book.Address.Book.Web.Application.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.address.book.Address.Book.Web.Application.DAO.ContactRepo;
import com.address.book.Address.Book.Web.Application.DAO.PersonRepo;
import com.address.book.Address.Book.Web.Application.Entity.Person;
import com.address.book.Address.Book.Web.Application.Response.CustomResponseCodes;
import com.address.book.Address.Book.Web.Application.Response.CustomResponseStatus;
import com.address.book.Address.Book.Web.Application.Response.JSONCustomResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
	private PersonRepo person_repo;
	
	@Autowired
	private ContactRepo contact_repo;
	
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public JSONCustomResponse postPerson(@RequestBody Person person) {
		JSONCustomResponse jsonResponse = new JSONCustomResponse();
		person_repo.save(person);
		jsonResponse.setStatus(CustomResponseStatus.SUCCESS);
		jsonResponse.setCode(CustomResponseCodes.created);
		jsonResponse.setMessage(" successfully created.");
		return jsonResponse;
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.PUT)
	public JSONCustomResponse putPerson(@RequestBody Person person) {
		JSONCustomResponse jsonResponse = new JSONCustomResponse();
		try {
		Person person_db = person_repo.findById(person.getId()).get();
		person_db.setAddress(person.getAddress());
		person_db.setDOB(person.getDOB());
		person_db.setEmail(person.getEmail());
		person_db.setFirstName(person.getFirstName());
		person_db.setLastName(person.getLastName());
		person_db.setNationality(person.getNationality());
		person_db.setPhone(person.getPhone());
		person_db.setTitle(person.getTitle());
		person_repo.save(person_db);
		jsonResponse.setStatus(CustomResponseStatus.SUCCESS);
		jsonResponse.setCode(CustomResponseCodes.created);

		jsonResponse.setMessage(" successfully updated.");
		}
		catch(Exception e){
			jsonResponse.setStatus(CustomResponseStatus.FAILURE);
			jsonResponse.setCode(CustomResponseCodes.internal_server_eror);
			jsonResponse.setMessage("Unable to update.");
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.DELETE)
	public JSONCustomResponse deletePerson(@RequestParam int id) {
		JSONCustomResponse jsonResponse = new JSONCustomResponse();
		try {
			Person person = person_repo.findById(id).get();
			person_repo.delete(person);
			jsonResponse.setStatus(CustomResponseStatus.SUCCESS);
			jsonResponse.setCode(CustomResponseCodes.created);
			jsonResponse.setMessage(" successfully created.");
		}
		catch(Exception e) {
			jsonResponse.setStatus(CustomResponseStatus.FAILURE);
			jsonResponse.setCode(CustomResponseCodes.internal_server_eror);
			jsonResponse.setMessage("Unable to update.");
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public JSONCustomResponse getAllPersons() {
		List<Person> l = new ArrayList<Person>();
		JSONCustomResponse jsonResponse = new JSONCustomResponse();
		try {
			for(Person t : person_repo.findAll()) {
				l.add(t);
			}
			jsonResponse.getData().put("person", l);
			jsonResponse.setStatus(CustomResponseStatus.SUCCESS);
			jsonResponse.setCode(CustomResponseCodes.ok);
			jsonResponse.setMessage(" successfully created.");
		}
		catch(Exception e){
			jsonResponse.setStatus(CustomResponseStatus.FAILURE);
			jsonResponse.setCode(CustomResponseCodes.internal_server_eror);
			jsonResponse.setMessage("Unable to fetch the list.");
		}
		return jsonResponse;
	}

}
