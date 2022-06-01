package com.qa.climbtracker.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.climbtracker.domain.dao.ClimbDao;

@Repository
public interface ClimbRepo extends JpaRepository<ClimbDao, Long> {
	@Query("SELECT climb FROM ClimbDao climb WHERE climb.id = ?1")
	public List<ClimbDao> readId(Long id);

	@Query("SELECT climb FROM ClimbDao climb WHERE climb.userId = ?1")
	public List<ClimbDao> readUserId(Long userId);
	
	@Query("SELECT climb FROM ClimbDao climb WHERE climb.routeId = ?1")
	public List<ClimbDao> readRouteId(Long routeId);
	
	@Query("SELECT climb FROM ClimbDao climb WHERE climb.userId = ?1 AND climb.dateClimbed = ?2")
	public List<ClimbDao> readDate(Long userId, Date date);
	
	@Query("SELECT climb FROM ClimbDao climb WHERE climb.userId = ?1 AND climb.completedClimb = ?2")
	public List<ClimbDao> readCompleted(Long userId, Boolean completed);
	
	@Modifying
	@Query("DELETE FROM ClimbDao climb WHERE climb.id IN ?1")
	public void delete(List<Long> ids);
	
	@Query("SELECT climb FROM ClimbDao climb WHERE climb.id IN ?1")
	public List<ClimbDao> exists(List<Long> ids);
}
