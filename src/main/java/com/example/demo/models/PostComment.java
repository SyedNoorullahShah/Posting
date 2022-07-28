package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted='0'")
@SQLDelete(sql = "update post_comment SET is_deleted = '1' WHERE id=?")
public class PostComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String review;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Post post;

  @OneToMany(mappedBy = "postComment", cascade = CascadeType.ALL, orphanRemoval = true)
  List<PostCommentReaction> commentReactions;

  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted;

  public PostComment addCommentReaction(PostCommentReaction commentReaction) {
    if (Objects.isNull(commentReactions)) {
      commentReactions = new ArrayList<>();
    }
    commentReaction.setPostComment(this);
    commentReactions.add(commentReaction);

    return this;
  }

  public void removeCommentReaction(PostCommentReaction commentReaction) {
    if(Objects.nonNull(commentReactions) && Objects.nonNull(commentReaction)) {
      commentReaction.setPostComment(null);
      commentReactions.remove(commentReaction);
    }
  }

  public void removeAllCommentReactions() {
    for (Iterator<PostCommentReaction> reactionIterator = commentReactions.iterator();
         reactionIterator.hasNext(); ) {
      PostCommentReaction reaction = reactionIterator.next();
      reaction.setPostComment(null);
      reactionIterator.remove();
    }
  }
}
