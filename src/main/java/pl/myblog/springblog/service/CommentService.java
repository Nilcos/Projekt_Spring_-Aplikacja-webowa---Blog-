package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.Comment;
import pl.myblog.springblog.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;




    public List<Comment> getAllComment() {
        return commentRepository.findAllByOrderByDateDesc();
    }

//
//    public Optional<Comment> getOneCourse(long id) {
//
//        return commentRepository.findById(id);
//    }
//
//    public Post getPost(long id) {
//        return commentRepository.getOne(id);
//    }
//
//    public void deletePost(long id) {
//
//        commentRepository.deleteById(id);
//    }
//
//
//    public void addToDB(Post post) {
//
//        postRepository.save(post);
//    }
//
//    public void updateDB(Post post) {
//        postRepository.save(post);
//    }



}
