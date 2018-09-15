package com.juliuskrah.leaderboard;

import java.io.IOException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.juliuskrah.leaderboard.di.SparkModule;
import com.juliuskrah.leaderboard.routes.Routes;

/**
 * 
 * @author Julius Krah
 *
 */
public class Application {

	public static void main(String... cmd) throws IOException {
		Injector injector = Guice.createInjector(new SparkModule());
		Routes routes = injector.getInstance(Routes.class);
		routes.init();
	}

}
