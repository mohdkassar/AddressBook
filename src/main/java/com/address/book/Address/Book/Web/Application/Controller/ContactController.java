package com.address.book.Address.Book.Web.Application.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.address.book.Address.Book.Web.Application.DAO.ContactRepo;
import com.address.book.Address.Book.Web.Application.DAO.PersonRepo;
import com.address.book.Address.Book.Web.Application.Entity.Contact;
import com.address.book.Address.Book.Web.Application.Entity.Person;
import com.address.book.Address.Book.Web.Application.Response.CustomResponseCodes;
import com.address.book.Address.Book.Web.Application.Response.CustomResponseStatus;
import com.address.book.Address.Book.Web.Application.Response.JSONCustomResponse;
import com.address.book.Address.Book.Web.Application.Service.FilesStorageService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ContactController {

	
	@Autowired
	private PersonRepo person_repo;
	
	@Autowired
	private ContactRepo contact_repo;
	
	@Autowired
	FilesStorageService storageService;
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public JSONCustomResponse postPerson(@RequestBody Contact contact, @RequestParam int person_id) {
		JSONCustomResponse jsonResponse = new JSONCustomResponse();
		try {
			System.out.println(person_id);

			Person person = person_repo.findById(person_id).get();
			System.out.println(person.getFirstName());
			contact.setPerson(person);
			contact_repo.save(contact);
			jsonResponse.setStatus(CustomResponseStatus.SUCCESS);
			jsonResponse.setCode(CustomResponseCodes.created);
			jsonResponse.setMessage(" successfully created.");
		}
		catch(Exception e) {
			jsonResponse.setStatus(CustomResponseStatus.FAILURE);
			jsonResponse.setCode(CustomResponseCodes.internal_server_eror);
			jsonResponse.setMessage(" unable to create. ");
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.PUT)
	public JSONCustomResponse putContact(@RequestBody Contact contact) {
		JSONCustomResponse jsonResponse = new JSONCustomResponse();
		try {
		Contact contact_db = contact_repo.findById(contact.getId()).get();
		contact_db.setAddress(contact.getAddress());
		contact_db.setDOB(contact.getDOB());
		contact_db.setEmail(contact.getEmail());
		contact_db.setFirstname(contact.getFirstname());
		contact_db.setLastname(contact.getLastname());
		contact_db.setNationality(contact.getNationality());
		contact_db.setPhone(contact.getPhone());
		contact_db.setJobtitle(contact.getJobtitle());
		contact_repo.save(contact_db);
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
	
	@RequestMapping(value = "/contact", method = RequestMethod.DELETE)
	public JSONCustomResponse deleteContact(@RequestParam int id) {
		JSONCustomResponse jsonResponse = new JSONCustomResponse();
		try {
			Contact contact = contact_repo.findById(id).get();
			contact_repo.delete(contact);
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
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public JSONCustomResponse getContactsByPersonID(@RequestParam int person_id, @RequestParam int page) {
		List<Contact> l = new ArrayList<Contact>();
		JSONCustomResponse jsonResponse = new JSONCustomResponse();
		try {
			System.out.println(person_id);
			ArrayList<Contact> contacts = contact_repo.findByPersonID(person_id, page*10).get();
			l.addAll(contacts);
			jsonResponse.getData().put("contact", l);
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

	@RequestMapping(value = "/contact/upload", method = RequestMethod.POST)
	public JSONCustomResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam int person_id) {
		String message = "";
		JSONCustomResponse jsonResponse = new JSONCustomResponse();
		Contact contact_db = contact_repo.findById(person_id).get();
		try {
			storageService.save(file,person_id);
			contact_db.setImage("C:/AddressBook/uploads/"+person_id+"/"+file.getOriginalFilename());
			contact_repo.save(contact_db);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			jsonResponse.setStatus(CustomResponseStatus.SUCCESS);
			jsonResponse.setCode(CustomResponseCodes.created);
			jsonResponse.setMessage(message);
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			jsonResponse.setStatus(CustomResponseStatus.FAILURE);
			jsonResponse.setCode(CustomResponseCodes.internal_server_eror);
			jsonResponse.setMessage(message);
            System.out.println(e.toString());
		}
		return jsonResponse;
	}
}
