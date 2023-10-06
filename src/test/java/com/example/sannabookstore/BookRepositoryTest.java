package com.example.sannabookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import com.example.sannabookstore.domain.Book;
import com.example.sannabookstore.domain.BookRepository;
import com.example.sannabookstore.domain.Category;
import com.example.sannabookstore.domain.CategoryRepository;

//@DataJpaTest

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SannabookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;
    
    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("John Carter");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Edgar Rice Burroughs");
    }
    
    @Test
    public void createNewBook() {
    	Category category = new Category("Adventure");
    	crepository.save(category);
    	Book book = new Book("Tove Jansson", "Taikurin hattu", "65465465-654" ,1986, 15, category);
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
    @Test
    public void deleteNewBook() {
		List<Book> books = repository.findByTitle("John Carter");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks = repository.findByTitle("John Carter");
		assertThat(newBooks).hasSize(0);
     }

}
