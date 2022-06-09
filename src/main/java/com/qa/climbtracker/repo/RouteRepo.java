package com.qa.climbtracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.climbtracker.domain.model.Route;

@Repository
public interface RouteRepo extends JpaRepository<Route, Long> {
	@Query("SELECT route FROM Route route WHERE route.id = ?1")
	public List<Route> readId(Long id);
	
	@Query("SELECT route FROM Route route WHERE route.name = ?1")
	public List<Route> readName(String name);

	@Query("SELECT route FROM Route route WHERE route.grade = ?1")
	public List<Route> readGrade(String grade);
	
	@Modifying
	@Query("DELETE FROM Route route WHERE route.id IN ?1")
	public void delete(List<Long> ids);
	
	@Query("SELECT route FROM Route route WHERE route.id IN ?1")
	public List<Route> exists(List<Long> ids);
}
