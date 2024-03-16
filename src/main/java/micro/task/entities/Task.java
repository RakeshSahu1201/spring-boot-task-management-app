package micro.task.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Task {

	@Id
	private String id;
	private String title;
	private String description;
	private long startTaskDateAndTime;
	private long endTaskDateAndTime;
	private String status;

	@ManyToOne
	@JsonBackReference
	private User user;

	// other needed properties;

}
