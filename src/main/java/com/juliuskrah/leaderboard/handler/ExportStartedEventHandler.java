package com.juliuskrah.leaderboard.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.juliuskrah.leaderboard.event.ExportStartedEvent;
import com.juliuskrah.leaderboard.event.framework.Handler;

/**
 * Handles the {@link ExportStartedEvent} message.
 * 
 * @author Julius Krah
 */
public class ExportStartedEventHandler implements Handler<ExportStartedEvent> {
	private static final Logger log = LoggerFactory.getLogger(ExportStartedEventHandler.class);

	@Override
	public void onEvent(ExportStartedEvent event) {
		log.info("Task '{}' has been started!", event.getTask().getId());
	}

}
