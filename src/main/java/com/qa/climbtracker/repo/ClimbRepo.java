package com.qa.climbtracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.climbtracker.domain.model.Climb;

@Repository
public interface ClimbRepo extends JpaRepository<Climb, Long> {
	@Query("SELECT climb FROM Climb climb WHERE climb.id = ?1")
	public List<Climb> readId(Long id);

	@Query("SELECT climb FROM Climb climb WHERE climb.user.id = ?1")
	public List<Climb> readUserId(Long userId);

	@Query("SELECT climb FROM Climb climb WHERE climb.user.id = ?1 AND climb.route.id = ?2")
	public List<Climb> readRouteId(Long userId, Long routeId);

	@Query("SELECT climb FROM Climb climb WHERE climb.user.id = ?1 AND climb.attempts = ?2")
	public List<Climb> readAttempts(Long userId, Integer attempts);

	@Modifying
	@Query("DELETE FROM Climb climb WHERE climb.id IN ?1")
	public void delete(List<Long> ids);

	@Query("SELECT climb FROM Climb climb WHERE climb.id IN ?1")
	public List<Climb> exists(List<Long> ids);
}
