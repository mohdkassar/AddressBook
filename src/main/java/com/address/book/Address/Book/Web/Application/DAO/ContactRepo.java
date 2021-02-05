package com.address.book.Address.Book.Web.Application.DAO;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.address.book.Address.Book.Web.Application.Entity.Contact;

public interface ContactRepo extends CrudRepository<Contact, Integer> {
	@Query(nativeQuery = true, value = "select * from contact where person_id=?1")
	public Optional<ArrayList<Contact>> findByPersonID(int id);
	
}
