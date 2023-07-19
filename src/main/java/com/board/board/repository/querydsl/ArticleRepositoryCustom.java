package com.board.board.repository.querydsl;

import java.util.List;

public interface ArticleRepositoryCustom {
  List<String> findAllDistinctHashtags();
}
