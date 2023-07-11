package com.board.board.dto;

import com.board.board.domain.Article;
import com.board.board.domain.ArticleComment;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.board.board.domain.ArticleComment}
 */
public record ArticleCommentDto(
    Long id,
    Long articleId,
    UserAccountDto userAccountDto,
    String content,
    LocalDateTime createdAt,
    String createdBy,
    LocalDateTime modifiedAt,
    String modifiedBy
    ){
public static ArticleCommentDto of(Long id,Long articleId,UserAccountDto userAccountDto,String content,LocalDateTime createdAt,String createdBy,LocalDateTime modifiedAt,String modifiedBy){
    return new ArticleCommentDto(id,articleId,userAccountDto,content,createdAt,createdBy,modifiedAt,modifiedBy);
    }

public static ArticleCommentDto from(ArticleComment entity){
    return new ArticleCommentDto(
    entity.getId(),
    entity.getArticle().getId(),
    UserAccountDto.from(entity.getArticle().getUserAccount()),
    entity.getContent(),
    entity.getCreatedAt(),
    entity.getCreatedBy(),
    entity.getModifiedAt(),
    entity.getModifiedBy()
    );
    }

public ArticleComment toEntity(Article entity){
    return ArticleComment.of(
        null,
        "content"
    );
    }

    public Long parentCommentId() {
        return null;
    }
}