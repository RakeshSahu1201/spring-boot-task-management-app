package micro.task.services;

import java.util.List;

import micro.task.entities.User;

public interface UserService {

	public User createUser(User User);

	public List<User> getAllUser();

	public User getUserById(String UserId);

	public User updateUser(User user, String userId);

	public void deleteUser(String userId);
}
