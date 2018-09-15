package com.juliuskrah.leaderboard;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.redisson.RedissonNode;
import org.redisson.config.Config;
import org.redisson.config.RedissonNodeConfig;

/**
 * 
 * @author Julius Krah
 *
 */
public class RedisConfiguration {

	public static void startEmbeddedRedissonNode(InputStream stream, int count) throws IOException {
		var config = Config.fromJSON(stream);
		var nodeConfig = new RedissonNodeConfig(config);
		nodeConfig.setExecutorServiceWorkers(Map.of("validator", count));
		var node = RedissonNode.create(nodeConfig);

		node.start();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> node.shutdown()));
	}

	public static void startEmbeddedRedissonNode(String configFile, int count) throws IOException {
		var stream = RedisConfiguration.class.getClassLoader().getResourceAsStream(configFile);
		startEmbeddedRedissonNode(stream, count);
	}
}
