package pl.myblog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.myblog.springblog.model.Comment;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.service.CommentService;
import pl.myblog.springblog.service.PostService;
import pl.myblog.springblog.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class PostController {
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    public PostController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String home(Model model, Authentication auth) {
        model.addAttribute("auth", auth);
        model.addAttribute("getAllPosts", postService.getAllPosts());
        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }


        return "index";

    }

    @GetMapping("/add-post")
    public String addPost(Model model, Authentication auth) {
        model.addAttribute("auth", auth);
        model.addAttribute("post", new Post());
        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }

        return "add-post";

    }

    @PostMapping("/save-post")
    public String savePost(@Valid @ModelAttribute Post post, BindingResult result, Model model) {

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("post", new Post());
            return "add-post";
        } else {
            postService.addToDB(post);
            model.addAttribute("postList", postService.getAllPosts());
            return "index";
        }
    }

    @GetMapping("/delete-post/{id}")
    public String deletePost(@PathVariable long id, Model model) {
        postService.deletePost(id);
        model.addAttribute("postList", postService.getAllPosts());
        return "index";
    }

    @GetMapping("/edit-post/{id}")
    public String editPost(@PathVariable long id, Model model) {
        Optional<Post> post = postService.getOnePost(id);
        model.addAttribute("post", post.get());
        return "post";
    }

    @GetMapping("/show-post/{id}")
    public String showPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        model.addAttribute("commentList", commentService.getAllComment(id));
        return "post";
    }

    @GetMapping("/delete-comment/{id}")
    public String deleteComment(@PathVariable long id) {
        Post post = commentService.getPostByCommentId(id);
        commentService.deleteComment(id);
//        model.addAttribute("post", post);
//        model.addAttribute("commentList", commentService.getAllComment(id));
        return "redirect:/show-post/" + post.getId();
    }

    @GetMapping("/edit-comment/{id}")
    public String editComment(@PathVariable long id, Model model) {
        Post post = commentService.getPostByCommentId(id);
        model.addAttribute("comment", commentService.getComment(id));
        return "redirect:/show-post/" + post.getId();
    }

    @PostMapping("/save-comment/{id}")
    public String saveComment(@Valid @ModelAttribute Post post, BindingResult result, Model model, long id) {

        Post post = commentService.getPostByCommentId(id);
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("post", new Comment());
            return "post";
        } else {
            postService.addToDB(post);
            model.addAttribute("commentList", postService.getAllPosts());
            return "index";
        }
    }
}
