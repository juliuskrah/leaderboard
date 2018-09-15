package com.juliuskrah.leaderboard.vo;

/**
 * 
 * @author Julius Krah
 *
 */
public class Message {
	private final int statusCode;
	private final String content;

	public Message(int statusCode, String content) {
		this.statusCode = statusCode;
		this.content = content;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public String getContent() {
		return content;
	}

}
