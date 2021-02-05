package com.address.book.Address.Book.Web.Application.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.address.book.Address.Book.Web.Application.Response.CustomResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contact implements CustomResponseDTO {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstname;
	private String lastname;
	private String DOB;
	private String email;
	private String jobtitle;
	private String image;
	private String nationality;
	private String phone;
	private String address;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "person_id", nullable = true)
	@JsonIgnore
	private Person person;

}
