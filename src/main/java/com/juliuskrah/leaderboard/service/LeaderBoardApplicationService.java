package com.juliuskrah.leaderboard.service;

import static com.juliuskrah.leaderboard.util.SparkUtil.render;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.redisson.api.ExecutorOptions;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.juliuskrah.leaderboard.di.RedissonProvider;
import com.juliuskrah.leaderboard.event.ExportCompletedEvent;
import com.juliuskrah.leaderboard.event.ExportStartedEvent;
import com.juliuskrah.leaderboard.event.framework.EventDispatcher;
import com.juliuskrah.leaderboard.handler.ExportCompletedEventHandler;
import com.juliuskrah.leaderboard.handler.ExportStartedEventHandler;
import com.juliuskrah.leaderboard.task.UserCommitExportJob;
import com.juliuskrah.leaderboard.vo.Message;
import com.juliuskrah.leaderboard.vo.TaskVo;

import spark.Request;
import spark.Response;

/**
 * 
 * @author Julius Krah
 *
 */
@Singleton
public class LeaderBoardApplicationService implements ApplicationService {
	private static final Logger log = LoggerFactory.getLogger(LeaderBoardApplicationService.class);
	private final RedissonProvider<RedissonClient> redissonProvider;
	private final RedissonProvider<RedissonReactiveClient> redissonReactiveProvider;

	@Inject
	LeaderBoardApplicationService(RedissonProvider<RedissonClient> redissonProvider,
			RedissonProvider<RedissonReactiveClient> redissonReactiveProvider) {
		this.redissonProvider = redissonProvider;
		this.redissonReactiveProvider = redissonReactiveProvider;
	}

	/**
	 * Delegates processing to a Redisson Node process for handling. <br/>
	 * This method takes a Callable as parameter
	 * 
	 * @param task long running background task
	 */
	private <T> void processBackgroundTasks(Callable<T> task) {
		try {
			var dispatcher = new EventDispatcher();
			dispatcher.registerHandler(ExportStartedEvent.class, new ExportStartedEventHandler());
			dispatcher.registerHandler(ExportCompletedEvent.class, new ExportCompletedEventHandler());
			var options = ExecutorOptions.defaults();
			options.taskRetryInterval(10, TimeUnit.SECONDS);
			var redisson = redissonProvider.get();
			var executorService = redisson.getExecutorService("validator", options);
			var future = executorService.submitAsync(task);
			future.thenAccept(result -> {
				dispatcher.dispatch(new ExportCompletedEvent(new TaskVo(future.getTaskId(), "juliuskrah", true)));
			}).exceptionally(e -> {
				// TODO Handle exception
				log.error("Operation could not be processed due to an error", e);
				return null;
			});
		} catch (IOException e) {
			log.error("Could not get instance of Redisson", e);
		}
	}

	/**
	 * Delegates processing to a Redisson Node process for handling. <br/>
	 * This method takes a Runnable as parameter
	 * 
	 * @param task long running background task
	 */
	private void processBackgroundTasks(Runnable task) {
		try {
			var dispatcher = new EventDispatcher();
			dispatcher.registerHandler(ExportStartedEvent.class, new ExportStartedEventHandler());
			dispatcher.registerHandler(ExportCompletedEvent.class, new ExportCompletedEventHandler());
			var options = ExecutorOptions.defaults();
			options.taskRetryInterval(10, TimeUnit.SECONDS);
			var redisson = redissonProvider.get();
			var executorService = redisson.getExecutorService("validator", options);
			var future = executorService.submitAsync(task);
			future.thenAccept(result -> {
				dispatcher.dispatch(new ExportCompletedEvent(new TaskVo(future.getTaskId(), "juliuskrah", true)));
			}).exceptionally(e -> {
				// TODO Handle exception
				log.error("Operation could not be processed due to an error", e);
				return null;
			});
		} catch (IOException e) {
			log.error("Could not get instance of Redisson", e);
		}
	}

	@Override
	public Object exportCommits(Request request, Response response) {
		processBackgroundTasks(new UserCommitExportJob());
		return "Your commits are being processed, you will receive a notification once it has completed";
	}

	@Override
	public Object notFound(Request request, Response response) {
		return render(new HashMap<>(), "404");
	}

	@Override
	public Object welcome(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("users",
				Map.of(23, "juliuskrah", 43, "deborahashley", 123, "peterquansah", 56, "jacobaggrey", 345, "ericaddo",
						456, "jameswong", 432, "brucelee", 21, "victorayetey", 98, "karensmith", 344, "derrickkyle"));
		return render(model, "index");
	}
	
	@Override
	public Object login(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		return render(model, "login");
	}

	@Override
	public Object leaderBoards(Request request, Response response) {
		response.type("application/json");
		return new Message(200, "Hello all");
	}

	@Override
	public Object leaderBoard(Request request, Response response) {
		response.type("application/json");
		return new Message(200, "Hello " + request.params(":name"));
	}
}
