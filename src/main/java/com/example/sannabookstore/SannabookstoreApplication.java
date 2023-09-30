package com.example.sannabookstore;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.sannabookstore.domain.AppUserRepository;
import com.example.sannabookstore.domain.Book;
import com.example.sannabookstore.domain.BookRepository;
import com.example.sannabookstore.domain.Category;
import com.example.sannabookstore.domain.CategoryRepository;
import com.example.sannabookstore.domain.AppUser;

@SpringBootApplication
public class SannabookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(SannabookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SannabookstoreApplication.class, args);
	}
	//https://github.com/sannaroi/sannabookstore.git
	

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, AppUserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Magic"));
			crepository.save(new Category("Adventure"));
			crepository.save(new Category("Scifi"));

			repository.save(new Book("J.K Rowling", "Harry Potter", "030-2211-3-8756-07", 1997, 22,
					crepository.findByName("Magic").get(0)));
			repository.save(new Book("Edgar Rice Burroughs", "John Carter", "030-2630-0-85-0768", 1964, 16,
					crepository.findByName("Adventure").get(0)));
			
			AppUser user1 = new AppUser("user", "$2a$10$HlXrz2k0noJLiPSVlGqpCOnqzF/3WYej.owdHlC1LvCVT5gaOwe4y", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$wfAwJU9HwKZSO9J.4vFdgOcSIrrFGNwHeotKTeB6EGavAUAkEyESy", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
