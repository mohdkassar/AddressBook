package com.address.book.Address.Book.Web.Application.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.address.book.Address.Book.Web.Application.Entity.Person;

@Repository
public interface PersonRepo extends CrudRepository<Person, Integer>{

}
