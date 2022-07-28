package com.example.demo.services;

import com.example.demo.models.PostComment;
import com.example.demo.models.PostCommentReaction;
import com.example.demo.repositories.PostCommentReactionRepository;
import com.example.demo.repositories.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.constants.ReactionConstants.LIKE;
import static com.example.demo.constants.ReactionConstants.LOVE;

@Service
@RequiredArgsConstructor
public class PostCommentService {

  private final PostCommentRepository postCommentRepository;
  private final PostCommentReactionRepository postCommentReactionRepository;

  @Transactional
  public void deleteCommentReactions() {
    PostComment postComment = postCommentRepository.findById(2).orElseThrow();


    PostCommentReaction reaction1 = new PostCommentReaction();
    reaction1.setReactionId(LIKE.getId());
    reaction1.setScore(4);
    PostCommentReaction reaction2 = new PostCommentReaction();
    reaction2.setReactionId(LOVE.getId());
    reaction2.setScore(4);

    List<PostCommentReaction> reactions = List.of(reaction1, reaction2);
    //postComment.removeAllCommentReactions();
    postCommentReactionRepository.deleteAllByPostCommentId(postComment.getId());

    reactions.forEach(postComment::addCommentReaction);
  }
}
