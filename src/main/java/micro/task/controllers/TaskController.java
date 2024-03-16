package micro.task.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micro.task.entities.Task;
import micro.task.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllTasks() {
		List<Task> allTask = taskService.getAllTask();
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("status", HttpStatus.OK);
		response.put("data", allTask);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<Map<String, Object>> getTaskById(@PathVariable String taskId) {
		Task task = taskService.getTaskById(taskId);
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("status", HttpStatus.OK);
		response.put("data", task);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> addTask(@RequestBody Task task) {
		// TODO: process POST request
		Task taskCreated = taskService.createTask(task);

		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("status", HttpStatus.CREATED);
		response.put("data", taskCreated);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{taskId}")
	public ResponseEntity<Map<String, Object>> updateTaskById(@PathVariable String taskId, @RequestBody Task task) {
		// TODO: process PUT request
		Task taskUpdated = taskService.updateTask(task, taskId);
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("status", HttpStatus.OK);
		response.put("data", taskUpdated);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<Map<String, Object>> deleteTaskById(@PathVariable String taskId) {
		taskService.deleteTask(taskId);
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("status", HttpStatus.OK);
		response.put("data", "Task Delete Of Given Id");
		return ResponseEntity.ok(response);
	}

}
