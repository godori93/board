package com.board.board.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
    @Index(columnList = "content"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy"),
})

@Entity
public class ArticleComment extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @ManyToOne(optional = false)
  private Article article; // 게시글 (id)

  @Setter
  @JoinColumn(name = "userId")
  @ManyToOne(optional = false)
  private UserAccount userAccount;

  @Setter
  @Column(updatable = false)
  private Long parentCommentId; // 부모 댓글 ID

  @ToString.Exclude
  @OrderBy("createdAt ASC")
  @OneToMany(mappedBy = "parentCommentId", cascade = CascadeType.ALL)
  private Set<ArticleComment> childComments = new LinkedHashSet<>();

  @Setter
  @Column(nullable = false, length = 500)
  private String content; // 본문

  protected ArticleComment() {
  }

  private ArticleComment(Article article, UserAccount userAccount, Long parentCommentId, String content) {
    this.article = article;
    this.userAccount = userAccount;
    this.parentCommentId = parentCommentId;
    this.content = content;
  }

  public static ArticleComment of(Article article, UserAccount userAccount, String content) {
    return new ArticleComment(article, userAccount, null, content);
  }

  public void addChildComment(ArticleComment child) {
    child.setParentCommentId(this.getId());
    this.getChildComments().add(child);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArticleComment that)) {
      return false;
    }
    return this.getId() != null && this.getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId());
  }
}
