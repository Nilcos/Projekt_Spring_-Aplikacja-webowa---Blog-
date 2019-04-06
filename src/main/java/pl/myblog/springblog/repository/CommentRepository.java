package pl.myblog.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.myblog.springblog.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {


//    List<Comment> findAllByPostIdNotNullOrderByDateDesc(long id);


}
