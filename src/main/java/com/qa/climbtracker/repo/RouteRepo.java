package com.qa.climbtracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.climbtracker.domain.dao.RouteDao;

@Repository
public interface RouteRepo extends JpaRepository<RouteDao, Long> {
	@Query("SELECT route FROM RouteDao route WHERE route.id = ?1")
	public List<RouteDao> readId(Long id);
	
	@Query("SELECT route FROM RouteDao route WHERE route.name = ?1")
	public List<RouteDao> readName(String name);

	@Query("SELECT route FROM RouteDao route WHERE route.grade = ?1")
	public List<RouteDao> readGrade(String grade);
	
	@Modifying
	@Query("DELETE FROM RouteDao route WHERE route.id IN ?1")
	public void delete(List<Long> ids);
	
	@Query("SELECT route FROM RouteDao route WHERE route.id IN ?1")
	public List<RouteDao> exists(List<Long> ids);
}
