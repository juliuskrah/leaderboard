package com.juliuskrah.leaderboard.event;

import com.juliuskrah.leaderboard.vo.TaskVo;

public class ExportStartedEvent extends AbstractEvent {
	private TaskVo task;

	public ExportStartedEvent(TaskVo task) {
		this.task = task;
	}

	public TaskVo getTask() {
		return task;
	}
}
