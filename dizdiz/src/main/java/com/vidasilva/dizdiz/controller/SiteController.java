package com.vidasilva.dizdiz.controller;

import com.vidasilva.dizdiz.model.Comment;
import com.vidasilva.dizdiz.model.Post;
import com.vidasilva.dizdiz.service.CommentService;
import com.vidasilva.dizdiz.service.PostService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SiteController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        return "post";
    }

    @PostMapping("/posts/{id}/comment")
    public String addComment(@PathVariable Long id, @ModelAttribute Comment comment, Principal principal) {
        Post post = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(post);
        commentService.createComment(comment);
        return "redirect:/posts/" + id;
    }
}
