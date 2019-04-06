package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.Comment;
import pl.myblog.springblog.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;


    public List<Comment> getAllComment(Long id) {
        return commentRepository.findAllByPostIdOrderByDateDesc(id);
    }


    public Optional<Comment> getOneComment(long id) {

        return commentRepository.findById(id);
    }

    public Comment getComment(long id) {
        return commentRepository.getOne(id);
    }

    public void deleteComment(long id) {

        commentRepository.deleteById(id);
    }


    public void addToDB(Comment comment) {
        comment.setDate(java.time.LocalDateTime.now().plusHours(1));
        commentRepository.save(comment);
    }

    public void updateDB(Comment comment) {
        commentRepository.save(comment);
    }



}
