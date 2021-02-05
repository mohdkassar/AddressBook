package com.address.book.Address.Book.Web.Application.DAO;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.address.book.Address.Book.Web.Application.Entity.Contact;

public interface ContactRepo extends CrudRepository<Contact, Integer> {
	@Query(nativeQuery = true, value = "select * from contact where person_id=?1 order by id OFFSET ?2 ROWS FETCH NEXT 10 ROWS ONLY")
	public Optional<ArrayList<Contact>> findByPersonID(int id, int offset);
	
	@Query(nativeQuery = true, value = "select * from contact where person_id=?1 and jobtitle=?3 order by id OFFSET ?2 ROWS FETCH NEXT 10 ROWS ONLY")
	public Optional<ArrayList<Contact>> filterByTitle(int id, int offset, String jobtitle);
	
	@Query(nativeQuery = true, value = "select * from contact where person_id=?1 and nationality=?3 order by id OFFSET ?2 ROWS FETCH NEXT 10 ROWS ONLY")
	public Optional<ArrayList<Contact>> filterByNationality(int id, int offset, String nationality);
	
	@Query(nativeQuery = true, value = "select * from contact where person_id=?1 and nationality=?3 and jobtitle = ?4 order by id OFFSET ?2 ROWS FETCH NEXT 10 ROWS ONLY")
	public Optional<ArrayList<Contact>> filterByNationalityAndTitle(int id, int offset, String nationality, String jobtitle);
}
