package com.juliuskrah.leaderboard.routes;

import static spark.Spark.get;
import static spark.Spark.notFound;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.redirect;
import static spark.Spark.staticFileLocation;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliuskrah.leaderboard.service.ApplicationService;

/**
 * 
 * @author Julius Krah
 *
 */
@Singleton
public class SparkRoutes implements Routes {
	private final ApplicationService applicationService;
	private final ObjectMapper mapper;

	@Inject
	SparkRoutes(ApplicationService applicationService, ObjectMapper mapper) {
		this.mapper = mapper;
		this.applicationService = applicationService;
	}

	@Override
	public void init() {
		port(8080); // Spark will run on port 8080

		staticFileLocation("public");

		notFound(applicationService::notFound);

		redirect.get("/", "/index");

		get("/index", applicationService::welcome);
		
		get("/login", applicationService::login);

		get("/leaderboard", applicationService::leaderBoards, mapper::writeValueAsString);

		get("/leaderboard/:name", applicationService::leaderBoard, mapper::writeValueAsString);

		post("/export", applicationService::exportCommits);
	}
}
