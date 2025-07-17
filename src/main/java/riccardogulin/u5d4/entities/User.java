package riccardogulin.u5d4.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE) // Toglie il setter per l'id
	private long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@Column(nullable = false)
	private String email;
	private int age;
	@Column(name = "profile_img")
	private String profileImg;

	public User(String name, String surname, String email, int age) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.age = age;
	}
}
