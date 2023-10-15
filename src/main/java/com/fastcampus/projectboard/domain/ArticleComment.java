package com.fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) private Article article; // 게시글 (id)
    @Setter @Column(nullable = false, length = 500) private String content; // 본문

    @CreatedDate @Column(nullable = false) LocalDateTime createdAt; // 생성일시
    @CreatedBy private String createdBy; // 생성자

    @CreatedDate @Column(nullable = false) private LocalDateTime modifiedAt; // 수정일시
    @CreatedBy private String modifiedBy; // 수정자

    protected ArticleComment() {}

    public ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    private static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
