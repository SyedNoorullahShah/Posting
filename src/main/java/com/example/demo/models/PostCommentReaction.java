package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted='0'")
@SQLDelete(sql = "update post_comment_reaction SET is_deleted = '1' WHERE id=?")
public class PostCommentReaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_comment_id")
  private PostComment postComment;

  private Integer reactionId;

  private Integer score;

  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted;
}
