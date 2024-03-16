package micro.task.services.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micro.task.entities.User;
import micro.task.exceptions.ResourceNotFoundException;
import micro.task.repositories.UserRepository;
import micro.task.services.UserService;

@Service
public class ImplUserService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		String uniqueUserId = UUID.randomUUID().toString();
		user.setId(uniqueUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No User Found With The Given Id"));
	}

	@Override
	public User updateUser(User user, String userId) {
		// TODO Auto-generated method stub
		user.setId(userId);
		User oldUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No User Found With The Given Id"));

		// Use ModelMapper to merge the objects
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);

		User mergedUser = modelMapper.map(oldUser, User.class);
		modelMapper.map(user, mergedUser);

		return userRepository.save(mergedUser);
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
	}

}
