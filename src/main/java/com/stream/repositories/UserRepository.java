package com.stream.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stream.model.dto.UserDTO;
import com.stream.model.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

	
	Optional<User> findByEmail(String email);
	
	@Query("select u from User u where id = :id")
	Optional<UserDTO> findUserById(@Param("id") String id);
	
	@Modifying
	@Query("update User u set u.activated = true where u.id = :id")
	void activateUser(@Param("id") String id);
	
	
	@Modifying
	@Query("update User u set u.password = :pass where u.id = :id")
	void changePassword(@Param("id") String id, @Param("pass") String password);
	
	@Query("select u from User u")
	List<UserDTO> findAllUsers();
	
}
