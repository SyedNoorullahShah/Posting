package com.example.demo.repositories;

import com.example.demo.models.PostCommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentReactionRepository extends JpaRepository<PostCommentReaction, Integer> {

  void deleteAllByPostCommentId(int postCommentId);

}
