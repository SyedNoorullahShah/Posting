package com.example.demo.services;

import com.example.demo.models.Post;
import com.example.demo.models.PostComment;
import com.example.demo.models.PostCommentReaction;
import com.example.demo.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.demo.constants.ReactionConstants.LIKE;
import static com.example.demo.constants.ReactionConstants.LOVE;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
  private final PostRepository postRepository;

  public void addPost() {

    Post post = new Post();
    post.setTitle("Meme post");

    PostComment postComment1 = new PostComment();
    postComment1.setReview("Comment 1");
    PostComment postComment2 = new PostComment();
    postComment2.setReview("Comment 2");

    PostCommentReaction reaction1 = new PostCommentReaction();
    reaction1.setReactionId(LIKE.getId());
    reaction1.setScore(5);
    PostCommentReaction reaction2 = new PostCommentReaction();
    reaction2.setReactionId(LOVE.getId());
    reaction2.setScore(5);

    PostCommentReaction reaction3 = new PostCommentReaction();
    reaction3.setReactionId(LIKE.getId());
    reaction3.setScore(4);
    PostCommentReaction reaction4 = new PostCommentReaction();
    reaction4.setReactionId(LOVE.getId());
    reaction4.setScore(4);

    postComment1.addCommentReaction(reaction1).addCommentReaction(reaction2);
    postComment2.addCommentReaction(reaction3).addCommentReaction(reaction4);

    post.addPostComment(postComment1).addPostComment(postComment2);
    System.out.println(postComment1.getId());

    postRepository.save(post);
  }

  public void removePostReaction() {
    Post post = postRepository.findById(2).orElseThrow();

    PostComment postComment = post.getPostComments().get(0);
    PostCommentReaction postCommentReaction = postComment.getCommentReactions().get(0);
    postComment.removeCommentReaction(postCommentReaction);

    postRepository.save(post);

  }

  public void deletePosts() {
    postRepository.deleteAll();
  }

  public void getPost() {
    Post post = postRepository.findById(4).orElseThrow();
    post.getPostComments()
        .forEach(postComment -> postComment.getCommentReactions()
            .forEach(commentReaction -> log.info("" + commentReaction.getId())));
  }
}
