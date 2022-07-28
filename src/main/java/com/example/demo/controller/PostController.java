package com.example.demo.controller;

import com.example.demo.services.PostCommentService;
import com.example.demo.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;
  private final PostCommentService postCommentService;

  @PostMapping
  public void addPost() {
    postService.addPost();
  }

  @DeleteMapping
  public void deletePosts() {
    postService.deletePosts();
  }

  @GetMapping
  public void getPost() {
    postService.getPost();
  }

  @DeleteMapping("/delete-reaction")
  public void removePostReaction() {
    postService.removePostReaction();
  }

  @DeleteMapping("/delete-comment-reactions")
  public void removeCommentReactions() {
    postCommentService.deleteCommentReactions();
  }

}
