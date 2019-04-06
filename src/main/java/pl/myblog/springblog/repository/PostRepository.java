package pl.myblog.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.myblog.springblog.model.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
}
