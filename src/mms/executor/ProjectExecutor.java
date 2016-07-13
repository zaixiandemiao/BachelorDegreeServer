package mms.executor;

import org.springframework.core.task.TaskExecutor;

public class ProjectExecutor {
	
	private TaskExecutor taskExecutor;

	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	
	
	public void watch(Runnable runner) {
		System.out.println("main Executor is running .... ");
		taskExecutor.execute(runner);
	}
	

}
