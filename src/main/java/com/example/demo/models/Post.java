package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@Where(clause = "is_deleted='0'")
@SQLDelete(sql = "update post SET is_deleted = '1' WHERE id=?")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;

  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PostComment> postComments;

  public Post addPostComment(PostComment postComment) {
    if (Objects.isNull(postComments)) {
      postComments = new ArrayList<>();
    }
    postComments.add(postComment);
    postComment.setPost(this);

    return this;
  }

  public void removePostComment(PostComment postComment) {
    if(Objects.nonNull(postComments) && Objects.nonNull(postComment)) {
      postComment.setPost(null);
      postComments.remove(postComment);
    }
  }

}
