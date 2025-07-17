package riccardogulin.u5d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardogulin.u5d4.entities.BlogPost;

@Repository
public interface BlogsRepository extends JpaRepository<BlogPost, Long> {
}
