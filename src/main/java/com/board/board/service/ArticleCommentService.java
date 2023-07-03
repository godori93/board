package com.board.board.service;

import com.board.board.dto.ArticleCommentDto;
import com.board.board.repository.ArticleCommentRepository;
import com.board.board.repository.ArticleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

  private final ArticleRepository articleRepository;
  private final ArticleCommentRepository articleCommentRepository;

  @Transactional(readOnly = true)
  public List<ArticleCommentDto> searchArticleComments(Long articleId) {
    return List.of();
  }

  public void saveArticleComment(ArticleCommentDto dto) {
  }

  public void updateArticleComment(ArticleCommentDto dto) {
  }

  public void deleteArticleComment(Long articleCommentId) {
  }

}
