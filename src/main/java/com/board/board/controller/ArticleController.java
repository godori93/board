package com.board.board.controller;

import com.board.board.domain.type.SearchType;
import com.board.board.dto.response.ArticleResponse;
import com.board.board.dto.response.ArticleWithCommentsResponse;
import com.board.board.service.ArticleService;
import com.board.board.service.PaginationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController  {

  private final ArticleService articleService;
  private final PaginationService paginationService;

  @GetMapping
  public String articles(
      @RequestParam(required = false) SearchType searchType,
      @RequestParam(required = false) String searchValue,
      @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable,
      ModelMap map) {

    Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
    List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());

    map.addAttribute("articles", articles);
    map.addAttribute("paginationBarNumbers", barNumbers);
    map.addAttribute("searchType", SearchType.values());

    return "articles/index";
  }

  @GetMapping("/{articleId}")
  public String article(@PathVariable Long articleId, ModelMap map) {
    ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticle(articleId));
    map.addAttribute("article", article);
    map.addAttribute("articleComments", article.articleCommentsResponse());
    map.addAttribute("totalCount", articleService.getArticleCount());

    return "articles/detail";
  }

  @GetMapping("/search-hashtag")
  public String searchHashtag(
      @RequestParam(required = false) String searchValue,
      @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
      ModelMap map
  ) {
    Page<ArticleResponse> articles = articleService.searchArticlesViaHashtag(searchValue, pageable).map(ArticleResponse::from);
    List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());
    List<String> hashtags = articleService.getHashtags();

    map.addAttribute("articles", articles);
    map.addAttribute("hashtags", hashtags);
    map.addAttribute("paginationBarNumbers", barNumbers);
    map.addAttribute("searchType", SearchType.HASHTAG);

    return "articles/search-hashtag";
  }
}
