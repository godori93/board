package com.board.board.service;

import com.board.board.domain.type.SearchType;
import com.board.board.dto.ArticleDto;
import com.board.board.dto.ArticleUpdateDto;
import com.board.board.repository.ArticleRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

  private final ArticleRepository articleRepository;

  @Transactional(readOnly = true)
  public Page<ArticleDto> searchArticles(SearchType title, String searchKeyword) {
    return Page.empty();
  }

  @Transactional(readOnly = true)
  public ArticleDto searchArticle(long l) {
    return null;
  }

  public void saveArticle(ArticleDto dto ) {
  }

  public void updateArticle(long articleId, ArticleUpdateDto dto) {
  }

  public void deleteArticle(long articleId) {
  }
}
