package com.qa.climbtracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.climbtracker.domain.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	@Query("SELECT user FROM User user WHERE user.id = ?1")
	public List<User> readId(Long id);

	@Query("SELECT user FROM User user WHERE user.username = ?1")
	public List<User> readUsername(String username);
	
	@Modifying
	@Query("DELETE FROM User user WHERE user.id IN ?1")
	public void delete(List<Long> ids);
	
	@Query("SELECT user FROM User user WHERE user.id IN ?1")
	public List<User> exists(List<Long> ids);
}
