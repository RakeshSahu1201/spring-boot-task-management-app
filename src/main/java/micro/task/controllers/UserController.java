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

import micro.task.entities.User;
import micro.task.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllUsers() {
		List<User> allUsers = userService.getAllUser();
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("status", HttpStatus.OK);
		response.put("data", allUsers);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> getUserById(@PathVariable String userId) {
		User user = userService.getUserById(userId);
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("status", HttpStatus.OK);
		response.put("data", user);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> addUser(@RequestBody User user) {
		// TODO: process POST request
		User userCreated = userService.createUser(user);

		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("status", HttpStatus.CREATED);
		response.put("data", userCreated);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> updateUserById(@PathVariable String userId, @RequestBody User user) {
		// TODO: process PUT request
		User userUpdated = userService.updateUser(user, userId);
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("status", HttpStatus.OK);
		response.put("data", userUpdated);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> deleteUserById(@PathVariable String userId) {
		userService.deleteUser(userId);
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("status", HttpStatus.OK);
		response.put("data", "User Delete Of Given Id");
		return ResponseEntity.ok(response);
	}
}
