package com.board.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.board.board.domain.Article;
import com.board.board.domain.type.SearchType;
import com.board.board.dto.ArticleDto;
import com.board.board.dto.ArticleUpdateDto;
import com.board.board.repository.ArticleRepository;
import com.board.board.service.ArticleService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

 @InjectMocks private ArticleService sut;

 @Mock private ArticleRepository articleRepository;

 @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
 @Test
  void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
  // Given

  // When
  Page<ArticleDto> articles =  sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, ID, 닉네임, 해시테

  // Then
  assertThat(articles).isNotNull();

 }
 @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
 @Test
 void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
  // Given

  // When
  ArticleDto articles =  sut.searchArticle(1L); // 제목, 본문, ID, 닉네임, 해시테

  // Then
  assertThat(articles).isNotNull();

 }

 @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
 @Test
 void givenArticleInfo_whenSavingArticle_thenSavesArticle() {

  // Given
  given(articleRepository.save(ArgumentMatchers.any(Article.class))).willReturn(null);
  // When
  sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "Godori", "title", "content", "java"));

  // Then
  BDDMockito.then(articleRepository).should().save(ArgumentMatchers.any(Article.class));

 }

 @DisplayName("게시글 ID와 수정 정보를 입력하면, 게시글을 수정한다.")
 @Test
 void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle() {

  // Given
  given(articleRepository.save(ArgumentMatchers.any(Article.class))).willReturn(null);
  // When
  sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "#java"));

  // Then
  BDDMockito.then(articleRepository).should().save(ArgumentMatchers.any(Article.class));

 }

 @DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다.")
 @Test
 void givenArticleId_whenDeletingArticle_thenDeletesArticle() {

  // Given
  BDDMockito.willDoNothing().given(articleRepository).delete(ArgumentMatchers.any(Article.class));
  // When
  sut.deleteArticle(1L);

  // Then
  BDDMockito.then(articleRepository).should().delete(ArgumentMatchers.any(Article.class));

 }

}