package com.address.book.Address.Book.Web.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AddressBookWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressBookWebApplication.class, args);
	}

}
