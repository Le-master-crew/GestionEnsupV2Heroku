package eu.ensup.gestionscolairespringboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.ensup.gestionscolairespringboot.domaine.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
