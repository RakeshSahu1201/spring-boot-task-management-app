package micro.task.services.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micro.task.entities.Task;
import micro.task.exceptions.ResourceNotFoundException;
import micro.task.repositories.TaskRepository;
import micro.task.services.TaskService;

@Service
public class ImplTaskService implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task createTask(Task task) {
		// TODO Auto-generated method stub
		// Creating pre-define property for the task.
		String uniqueTaskId = UUID.randomUUID().toString();
		Instant now = Instant.now();
		long startTaskDateAndTime = now.toEpochMilli();

		task.setId(uniqueTaskId);
		task.setStatus(task.getStatus().toUpperCase());
		task.setStartTaskDateAndTime(startTaskDateAndTime);
		return taskRepository.save(task);
	}

	@Override
	public List<Task> getAllTask() {
		// TODO Auto-generated method stub
		return taskRepository.findAll();
	}

	@Override
	public Task getTaskById(String taskId) {
		// TODO Auto-generated method stub
		return taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("No Task Found With Given Id"));
	}

	@Override
	public List<Task> getTasksByUserId(String userId) {
		// TODO Auto-generated method stub
		return taskRepository.findByUserId(userId);
	}

	@Override
	public Task updateTask(Task task, String taskId) {
		// TODO Auto-generated method stub

		Task oldTask = getTaskById(taskId);
		System.out.println("-------------------BEFORE-OLD-TASK----------------------");
		System.out.println(task);

		task.setStartTaskDateAndTime(oldTask.getStartTaskDateAndTime());

		// Use ModelMapper to merge the objects
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);

		Task updateTask = modelMapper.map(oldTask, Task.class);
		modelMapper.map(task, updateTask);

		// creating end task time
		Instant now = Instant.now();
		long endTaskDateAndTime = now.toEpochMilli();

		System.out.println("--------------------OLD-TASK----------------------");
		System.out.println(task);
		System.out.println("---------------------NEW-TASK--------------------");
		System.out.println(oldTask);
		System.out.println("----------------------UPDATED-TASK-------------------");
		System.out.println(updateTask);

		System.out.println("___________________________________________________________");

		updateTask.setStartTaskDateAndTime(oldTask.getStartTaskDateAndTime());
		updateTask.setEndTaskDateAndTime(endTaskDateAndTime);
		updateTask.setStatus(task.getStatus().toUpperCase());
		return taskRepository.save(updateTask);
	}

	@Override
	public void deleteTask(String taskId) {
		// TODO Auto-generated method stub
		taskRepository.deleteById(taskId);
	}

}
