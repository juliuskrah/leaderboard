package com.juliuskrah.leaderboard.event;

import com.juliuskrah.leaderboard.event.framework.Event;

/**
 * The {@link AbstractEvent} class serves as a base class for defining custom
 * events happening with your system. In this example we have two types of
 * events defined.
 * <ul>
 * <li>{@link ExportCompletedEvent} - used when export has completed</li>
 * <li>{@link ExportStartedEvent} - used when export has started</li>
 * </ul>
 * Events can be distinguished using the {@link #getType() getType} method.
 * 
 * @author Julius Krah
 */
public abstract class AbstractEvent implements Event {

	/**
	 * Returns the event type as a {@link Class} object In this example, this method
	 * is used by the {@link EventDispatcher} to dispatch events depending on their
	 * type.
	 *
	 * @return the AbstractEvent type as a {@link Class}.
	 */
	@Override
	public Class<? extends Event> getType() {
		return getClass();
	}
}