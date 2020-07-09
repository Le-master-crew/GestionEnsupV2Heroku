package eu.ensup.gestionscolairespringboot.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import eu.ensup.gestionscolairespringboot.dao.UserRepository;
import eu.ensup.gestionscolairespringboot.domaine.User;
import eu.ensup.gestionscolairespringboot.service.UserDetailsServiceImpl;

class UserDetailsServiceImplTest {

	@Mock
	private UserRepository userdao;

	@Autowired
	@InjectMocks
	private UserDetailsServiceImpl userservice;

	@BeforeEach
	public void setupMock() {
		MockitoAnnotations.initMocks(this); // Cette méthode initialise également les objets simulés lors de
											// l'initialisation des tests junit.
	}

	@Test
	void loadUserByUsernameTestReturnAssociatedUser() {
		String username = "directeur";
		User user = new User();
		user.setUsername(username);
		when(userdao.findByUsername(username)).thenReturn(user);
		assertEquals("directeur", userservice.loadUserByUsername(username).getUsername());
	}

}
