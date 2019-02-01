package com.juliuskrah.leaderboard.service;

import spark.Request;
import spark.Response;

/**
 * 
 * @author Julius Krah
 *
 */
public interface ApplicationService {

	/**
	 * Exports the user commits and email the result
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	Object exportCommits(Request request, Response response);

	Object notFound(Request request, Response response);

	Object welcome(Request request, Response response);
	
	Object login(Request request, Response response);

	Object leaderBoards(Request request, Response response);

	Object leaderBoard(Request request, Response response);
}
