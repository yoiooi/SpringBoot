package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // 테이블과 링크될 클래스임을 나타냄, JPA를 사용하면 DB 데이터에 작업할 경우 Entity 클래스의 수정을 통해 작업함
public class Posts extends BaseTimeEntity {
    // 주의! Entity 클래스에서는 절대 Setter 메소드를 만들지 않음
    // 기본적으로 생성자를 통해 DB에 삽입하며, 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경해야 함

    @Id // 테이블의 PK필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙을 나타냄
    private Long id;

    @Column(length = 500, nullable = false) // 선언하지 않아도 클래스의 필드는 칼럼이 됨, but 기본값 외에 추가로 변경이 필요한 경우 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
