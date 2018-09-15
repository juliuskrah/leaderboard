package com.juliuskrah.leaderboard.di;

import java.io.IOException;

import com.google.inject.throwingproviders.CheckedProvider;

/**
 * 
 * @author Julius Krah
 *
 * @param <T>
 */
public interface RedissonProvider<T> extends CheckedProvider<T> {
	T get() throws IOException;
}
