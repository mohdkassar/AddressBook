package com.address.book.Address.Book.Web.Application.DAO;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.address.book.Address.Book.Web.Application.Entity.Contact;

public interface ContactRepo extends CrudRepository<Contact, Integer> {
	@Query(nativeQuery = true, value = "select * from contact where person_id=?1 order by id limit 10 offset ?2")
	@Cacheable("findByPersonID")
	public Optional<ArrayList<Contact>> findByPersonID(int id, int offset);
	
	@Query(nativeQuery = true, value = "select * from contact where person_id=?1 and jobtitle LIKE CONCAT('%', ?3 ,'%') order by id limit 10 offset ?2")
	public Optional<ArrayList<Contact>> filterByTitle(int id, int offset, String jobtitle);
	
	@Query(nativeQuery = true, value = "select * from contact where person_id LIKE CONCAT('%', ?1 ,'%') and nationality LIKE CONCAT('%', ?3 ,'%') order by id limit 10 offset ?2")
	public Optional<ArrayList<Contact>> filterByNationality(int id, int offset, String nationality);
	
	@Query(nativeQuery = true, value = "select * from contact where person_id LIKE CONCAT('%', ?1 ,'%') and nationality LIKE CONCAT('%', ?3 ,'%') and jobtitle LIKE CONCAT('%', ?4 ,'%') order by id limit 10 offset ?2")
	public Optional<ArrayList<Contact>> filterByNationalityAndTitle(int id, int offset, String nationality, String jobtitle);
}
