package pl.myblog.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.myblog.springblog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
