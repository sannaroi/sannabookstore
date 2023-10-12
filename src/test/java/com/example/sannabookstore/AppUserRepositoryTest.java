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

import com.example.sannabookstore.domain.AppUser;
import com.example.sannabookstore.domain.AppUserRepository;

@SpringBootTest(classes = SannabookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AppUserRepositoryTest {
	
	@Autowired
    private AppUserRepository urepository;

    @Test
    public void findByUsernameShouldReturnAppUser() {
        AppUser appusers = urepository.findByUsername("ADMIN");
       
        assertThat(appusers.getUsername()).isEqualTo("admin");
    }
    
    @Test
    public void createNewAppUser() {
    	AppUser appuser = new AppUser("testuser", "TESTUSER", "$2a$10$RoSelM03e2GES7MUbrUAtuq9jNVlg0Go02OeS7tXOU.D7i68V0TN.");
    	urepository.save(appuser);
    	assertThat(appuser.getId()).isNotNull();
    }    
}