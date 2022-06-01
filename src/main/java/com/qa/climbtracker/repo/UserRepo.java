package com.qa.climbtracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.climbtracker.domain.dao.UserDao;

@Repository
public interface UserRepo extends JpaRepository<UserDao, Long> {
	@Query("SELECT user FROM UserDao user WHERE user.id = ?1")
	public List<UserDao> readId(Long id);

	@Query("SELECT user FROM UserDao user WHERE user.username = ?1")
	public List<UserDao> readUsername(String username);
	
	@Modifying
	@Query("DELETE FROM UserDao user WHERE user.id IN ?1")
	public void delete(List<Long> ids);
	
	@Query("SELECT user FROM UserDao user WHERE user.id IN ?1")
	public List<UserDao> exists(List<Long> ids);
}
