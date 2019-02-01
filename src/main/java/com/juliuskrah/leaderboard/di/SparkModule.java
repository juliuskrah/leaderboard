package com.juliuskrah.leaderboard.di;

import java.io.IOException;

import javax.inject.Singleton;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.throwingproviders.CheckedProvides;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import com.juliuskrah.leaderboard.routes.Routes;
import com.juliuskrah.leaderboard.routes.SparkRoutes;
import com.juliuskrah.leaderboard.service.ApplicationService;
import com.juliuskrah.leaderboard.service.LeaderBoardApplicationService;

/**
 * 
 * @author Julius Krah
 *
 */
public class SparkModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Routes.class).to(SparkRoutes.class);
		bind(ApplicationService.class).to(LeaderBoardApplicationService.class);
		install(ThrowingProviderBinder.forModule(this));
	}

	@Singleton
	@Provides
	ObjectMapper provideObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		return mapper;
	}

	@Singleton
	@CheckedProvides(RedissonProvider.class)
	RedissonClient provideRedissonClient() throws IOException {
		var stream = SparkModule.class.getClassLoader().getResourceAsStream("standalone.json");
		try (stream) {
			var config = Config.fromJSON(stream);
			return Redisson.create(config);
		}
	}
	
	@Singleton
	@CheckedProvides(RedissonProvider.class)
	RedissonReactiveClient provideRedissonReactiveClient() throws IOException {
		var stream = SparkModule.class.getClassLoader().getResourceAsStream("standalone.json");
		try (stream) {
			var config = Config.fromJSON(stream);
			return Redisson.createReactive(config);
		}
	}
}
