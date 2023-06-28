package com.board.board.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.board.board.config.SecurityConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DisplayName("View 컨트롤러 - 게시글")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

  private final MockMvc mvc;

  public ArticleControllerTest(@Autowired MockMvc mvc) {
    this.mvc = mvc;
  }

  @DisplayName("[view] [Get] 게시글 리스트 (게시판) 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingArticlesView_thenReturnsAriclesView() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/articles"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(MockMvcResultMatchers.view().name("articles/index"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("articles"));

  }

  @DisplayName("[view] [Get] 게시글 상세 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingArticleView_thenReturnsAricleView() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/articles/1"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(MockMvcResultMatchers.view().name("articles/detail"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("article"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("articleComments"));

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

}
