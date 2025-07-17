package riccardogulin.u5d4.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardogulin.u5d4.entities.BlogPost;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.repositories.BlogsRepository;

@Service
@Slf4j
public class BlogsService {
	@Autowired
	private BlogsRepository blogsRepository;

	@Autowired
	private UsersService usersService;


	public void saveNewBlog(String blogTitle, String blogContent, long userId) {
		User author = this.usersService.findById(userId);
		BlogPost blog = new BlogPost(blogTitle, blogContent, author);
		blogsRepository.save(blog);
		log.info("Blog salvato correttamente!");
	}
}
