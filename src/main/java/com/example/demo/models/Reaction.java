package com.example.demo.models;

import com.example.demo.constants.ReactionConstants;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted='0'")
@SQLDelete(sql = "update reaction SET is_deleted = '1' WHERE id=?")
public class Reaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  private ReactionConstants constant;

  private String detail;

  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted;
}
