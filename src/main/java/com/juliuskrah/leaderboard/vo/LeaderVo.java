package com.juliuskrah.leaderboard.vo;

/**
 * LeaderVo represents the details of the user partaking in the leaderboard.
 * User with the highest number of commits wins the leaderboard
 * 
 * @author Julius Krah
 *
 */
public class LeaderVo {
	private final String id;
	private final String username;
	private final int numberOfCommits;
	private String photoUri;

	public LeaderVo(String id, String username, int numberOfCommits) {
		super();
		this.id = id;
		this.username = username;
		this.numberOfCommits = numberOfCommits;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public int getNumberOfCommits() {
		return numberOfCommits;
	}

	public String getPhotoUri() {
		return photoUri;
	}

	public void setPhotoUri(String photoUri) {
		this.photoUri = photoUri;
	}
}
