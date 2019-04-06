package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByDateDesc();
    }


    public Optional<Post> getOneCourse(long id) {

        return postRepository.findById(id);
    }

    public Post getPost(long id) {
        return postRepository.getOne(id);
    }

    public void deletePost(long id) {

        postRepository.deleteById(id);
    }


    public void addToDB(Post post) {

        postRepository.save(post);
    }

    public void updateDB(Post post) {
        postRepository.save(post);
    }





}
