package micro.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micro.task.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
