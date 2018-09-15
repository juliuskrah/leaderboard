package com.juliuskrah.leaderboard.event;

import com.juliuskrah.leaderboard.vo.TaskVo;

public class ExportCompletedEvent extends AbstractEvent {
	private TaskVo task;

	public ExportCompletedEvent(TaskVo task) {
		this.task = task;
	}

	public TaskVo getTask() {
		return task;
	}
}
