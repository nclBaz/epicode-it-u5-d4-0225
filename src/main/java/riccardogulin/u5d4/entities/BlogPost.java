package riccardogulin.u5d4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String content;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private User author;

	public BlogPost(String title, String content, User author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
}
