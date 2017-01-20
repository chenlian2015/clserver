package com.cnd.greencube.server.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 任务管理类
 * 
 * @author huxg
 * 
 */
@Component
public class TaskManager {
	@Resource(name = "taskExecutor")
	ThreadPoolTaskExecutor taskExecutor;

	List<Runnable> tasks = null;

	public void init() {
		for (Runnable r : tasks) {
			taskExecutor.execute(r);
		}
	}

	public List<Runnable> getTasks() {
		return tasks;
	}

	public void setTasks(List<Runnable> tasks) {
		this.tasks = tasks;
	}
}
