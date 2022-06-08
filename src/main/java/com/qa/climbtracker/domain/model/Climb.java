package com.qa.climbtracker.domain.model;

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
public class Climb {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	@ManyToOne(targetEntity = Route.class)
	@JoinColumn(name = "route_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Route route;
	private Integer timeTaken;
	private Integer attempts;
}

// id
// user id
// route id
// time taken
// completed climb
