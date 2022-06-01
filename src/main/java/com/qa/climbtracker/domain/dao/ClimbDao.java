package com.qa.climbtracker.domain.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ClimbDao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(targetEntity = UserDao.class)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserDao user;
	@ManyToOne(targetEntity = RouteDao.class)
	@JoinColumn(name = "route_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private RouteDao route;
	private Integer timeTaken;
	private Boolean completedClimb;
}

// id
// user id
// route id
// time taken
// completed climb
