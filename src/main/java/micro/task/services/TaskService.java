package micro.task.services;

import java.util.List;

import micro.task.entities.Task;

public interface TaskService {

	public Task createTask(Task task);

	public List<Task> getAllTask();

	public Task getTaskById(String taskId);

	public List<Task> getTasksByUserId(String userId);

	public Task updateTask(Task task, String taskId);

	public void deleteTask(String taskId);

}
