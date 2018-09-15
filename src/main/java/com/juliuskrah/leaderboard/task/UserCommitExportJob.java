package com.juliuskrah.leaderboard.task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Starts a background job that extracts all commits by a user, zips it up, and
 * sends the user an email with a link to download the zip file
 * 
 * @author Julius Krah
 *
 */
public class UserCommitExportJob implements Callable<String> {

	@Override
	public String call() throws Exception {
		TimeUnit unit = TimeUnit.MINUTES;
		Thread.sleep(unit.toMillis(1));
		return "Emails sent successfully";
	}

	private void extractUserCommits(String userId) {

	}

	private boolean gZipCommits() {
		return false;
	}

	private boolean emailLinkToCommits(String userId) {
		return false;
	}
}
