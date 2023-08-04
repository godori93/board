package com.board.board.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
    @Index(columnList = "hashtagName", unique = true),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})
@Entity
public class Hashtag extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ToString.Exclude
  @ManyToMany(mappedBy = "hashtags")
  private Set<Article> articles = new LinkedHashSet<>();

  @Setter
  @Column(nullable = false)
  private String hashtagName; // 해시태그 이름


  protected Hashtag() {
  }

  private Hashtag(String hashtagName) {
    this.hashtagName = hashtagName;
  }

  public static Hashtag of(String hashtagName) {
    return new Hashtag(hashtagName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Hashtag hashtag)) {
      return false;
    }
    return this.getId() != null && this.getId().equals(hashtag.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId());
  }
}
