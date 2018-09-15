package com.juliuskrah.leaderboard.vo;

public class TaskVo {
	private final String id;
	private final String username;
	private final boolean completed;
	private String description;

	public TaskVo(String id, String username, boolean completed) {
		super();
		this.id = id;
		this.username = username;
		this.completed = completed;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public boolean isCompleted() {
		return completed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
