package com.board.board.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.board.board.config.SecurityConfig;
import com.board.board.dto.ArticleWithCommentsDto;
import com.board.board.dto.UserAccountDto;
import com.board.board.service.ArticleService;
import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DisplayName("View 컨트롤러 - 게시글")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

  private final MockMvc mvc;

  @MockBean
  private ArticleService articleService;

  public ArticleControllerTest(@Autowired MockMvc mvc) {
    this.mvc = mvc;
  }

  @DisplayName("[view] [Get] 게시글 리스트 (게시판) 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingArticlesView_thenReturnsAriclesView() throws Exception {
    // Given
    given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());

    // When & Then
    mvc.perform(get("/articles"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(MockMvcResultMatchers.view().name("articles/index"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("articles"));
    BDDMockito.then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));

  }

  @DisplayName("[view] [Get] 게시글 상세 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingArticleView_thenReturnsAricleView() throws Exception {
    // Given
    Long articleId = 1L;
    given(articleService.getArticle(articleId)).willReturn(createArticleWithCommentsDto());

    // When & Then
    mvc.perform(get("/articles/" + articleId))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(MockMvcResultMatchers.view().name("articles/detail"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("article"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("articleComments"));
    BDDMockito.then(articleService).should().getArticle(articleId);

  }

  @Disabled("구현 중")
  @DisplayName("[view] [Get] 게시글 검색 전용 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingArticleSearchView_thenReturnsAricleSearchView() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/articles/search"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(MockMvcResultMatchers.view().name("articles/search"));

  }

  @Disabled("구현 중")
  @DisplayName("[view] [Get] 게시글 해시태그 검색 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnsAricleHashtagSearchView() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/articles/search-hashtag"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(MockMvcResultMatchers.view().name("articles/search-hashtag"));

  }

  private ArticleWithCommentsDto createArticleWithCommentsDto() {
    return ArticleWithCommentsDto.of(
        1L,
        createUserAccountDto(),
        Set.of(),
        "title",
        "content",
        "#java",
        LocalDateTime.now(),
        "godori",
        LocalDateTime.now(),
        "godori"
    );

  }

  private UserAccountDto createUserAccountDto() {
    return UserAccountDto.of(
        1L,
        "godori",
        "pw",
        "gelbbo93@mail.com",
        "godori",
        "memo",
        LocalDateTime.now(),
        "godori",
        LocalDateTime.now(),
        "godori"
        );
  }

}
