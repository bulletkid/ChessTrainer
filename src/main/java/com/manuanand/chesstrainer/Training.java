package com.manuanand.chesstrainer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Training {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private Integer playerId;

	private Integer puzzleId;
	
	private PuzzleStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer userId) {
		this.playerId = userId;
	}

	public Integer getPuzzleId() {
		return puzzleId;
	}

	public void setPuzzleId(Integer puzzleId) {
		this.puzzleId = puzzleId;
	}

	public PuzzleStatus getStatus() {
		return status;
	}

	public void setStatus(PuzzleStatus status) {
		this.status = status;
	}
}
