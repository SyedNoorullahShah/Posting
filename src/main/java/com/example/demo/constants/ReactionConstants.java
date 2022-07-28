package com.example.demo.constants;

import lombok.Getter;

@Getter
public enum ReactionConstants {
  LIKE(1), LOVE(2), HAHA(3), ANGRY(4);

  private final Integer id;

  ReactionConstants(int id) {
    this.id = id;
  }
}
