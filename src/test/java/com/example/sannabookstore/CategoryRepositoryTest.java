package com.example.sannabookstore;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.sannabookstore.domain.Category;
import com.example.sannabookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SannabookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository crepository;
	
	@Test
	public void findByNameShouldReturnCategory () {
		List<Category> categories = crepository.findByName("Magic");
		
		assertThat(categories).hasSize(1);
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Mystery");
		crepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}
	
}
