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
import pl.myblog.springblog.service.SubjectService;
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
    SubjectService subjectService;

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

    @GetMapping("/addpost")
    public String addPost(Model model, Authentication auth) {

        model.addAttribute("subject",subjectService.getAllCourses());
        model.addAttribute("auth", auth);
        model.addAttribute("post", new Post());
        model.addAttribute("postAdding", true);
        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }

        return "add-post";

    }

    @PostMapping("/savepost")
    public String savePost(@Valid @ModelAttribute Post post, BindingResult result, Model model, Authentication auth) {

        model.addAttribute("auth", auth);
        model.addAttribute("postAdded", true);

        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("post", post);
            model.addAttribute("postAdding", true);
            model.addAttribute("subject",subjectService.getAllCourses());
            return "add-post";

        } else {

            postService.addToDB(post, userService.getUserById(auth));
            model.addAttribute("getAllPosts", postService.getAllPosts());
            return "index";
        }
    }

    @PostMapping("/saveeditedpost")
    public String saveEditedPost(@Valid @ModelAttribute Post post, BindingResult result, Model model, Authentication auth) {

        model.addAttribute("auth", auth);

        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("post", post);
            model.addAttribute("postEditing", true);
            model.addAttribute("subject",subjectService.getAllCourses());
            return "add-post";
        } else {
            postService.addEditedToDB(post);
            model.addAttribute("getAllPosts", postService.getAllPosts());
            return "index";
        }
    }






    @GetMapping("/deletepost/{id}")
    public String deletePost(@PathVariable long id, Model model, Authentication auth) {

        model.addAttribute("auth", auth);

        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }

        postService.deletePost(id);
        model.addAttribute("getAllPosts", postService.getAllPosts());
        return "redirect:/";
    }

    @GetMapping("/editpost/{id}")
    public String editPost(@PathVariable long id, Model model, Authentication auth) {

        model.addAttribute("auth", auth);
        model.addAttribute("postEditing", true);
        model.addAttribute("subject",subjectService.getAllCourses());
        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }

        Optional<Post> post = postService.getOnePost(id);
        model.addAttribute("post", post.get());
        return "add-post";
    }

    @GetMapping("/show-post/{id}")
    public String showPost(@PathVariable long id, Model model, Authentication auth) {

        model.addAttribute("auth", auth);
        model.addAttribute("commentAdding", true);

        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }

        Post post = postService.getPost(id);

        model.addAttribute("post", post);
        model.addAttribute("commentList",commentService.getAllCommentbyPostId(post));
        model.addAttribute("comment", new Comment());
        return "post";
    }

    @GetMapping("/delete-comment/{id}")
    public String deleteComment(@PathVariable long id, Model model, Authentication auth) {

        model.addAttribute("auth", auth);

        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }

        Post post = commentService.getPostByCommentId(id);
        commentService.deleteComment(id);
//        model.addAttribute("post", post);
//        model.addAttribute("commentList", commentService.getAllComment(id));
        return "redirect:/show-post/" + post.getId();
    }

    @GetMapping("/edit-comment/{id}")
    public String editComment(@PathVariable long id, Model model, Authentication auth) {

        model.addAttribute("auth", auth);
        model.addAttribute("commentEditing", true);

        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }

        Post post = commentService.getPostByCommentId(id);
        model.addAttribute("comment", commentService.getComment(id));
        model.addAttribute("post", post);
        model.addAttribute("commentList",commentService.getAllCommentbyPostId(post));
//        return "redirect:/show-post/" + post.getId();
        return "post";
    }

    @PostMapping("/savecomment/{id}")
    public String saveComment(@Valid @ModelAttribute Comment comment, BindingResult result, Model model, @PathVariable long id, Authentication auth) {

        model.addAttribute("auth", auth);

        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }
        Post post = postService.getPost(id);
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));


            model.addAttribute("post", post);
            model.addAttribute("commentList",commentService.getAllCommentbyPostId(post));
            model.addAttribute("commentNew", new Comment());

            System.out.println("TO SIE WYSWIETLA");
            return "post";
        } else {


            commentService.addToDB(comment);

            return "redirect:/show-post/"+ post.getId();
        }
    }
}
