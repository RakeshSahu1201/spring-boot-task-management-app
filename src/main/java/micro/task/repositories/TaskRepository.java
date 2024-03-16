package micro.task.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micro.task.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

	public List<Task> findByUserId(String userId);
}
