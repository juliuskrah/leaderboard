package com.juliuskrah.leaderboard.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.juliuskrah.leaderboard.event.ExportCompletedEvent;
import com.juliuskrah.leaderboard.event.framework.Handler;

/**
 * Handles the {@link ExportCompletedEvent} message.
 * 
 * @author Julius Krah
 */
public class ExportCompletedEventHandler implements Handler<ExportCompletedEvent> {
	private static final Logger log = LoggerFactory.getLogger(ExportCompletedEventHandler.class);

	@Override
	public void onEvent(ExportCompletedEvent event) {
		log.info("Task '{}' has been completed!", event.getTask().getId());
	}

}
