package com.example.springdatajpa.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
public class Board {
    @Id
    @Column(name = "board_id")
    private Integer boardId;

    @Column(length = 100)
    private String title;

    @Lob // 이걸 붙여줘야 대용량 텍스트로 인지한다.
    private String content; // text type

    @CreationTimestamp
    private LocalDateTime regdate;

    private Integer viewCnt;

    @ManyToOne(fetch = FetchType.LAZY) // 게시물 N --- 1 사용자, FetchType.EAGER(안써줘도 기본값이다) -> 무조건 데이터를 가지고와라.
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", regdate=" + regdate +
                ", viewCnt=" + viewCnt +
                '}';
    }
}

//'board_id', 'int', 'NO', 'PRI', NULL, 'auto_increment'
//'title', 'varchar(100)', 'NO', '', NULL, ''
//'content', 'text', 'YES', '', NULL, ''
//'user_id', 'int', 'NO', 'MUL', NULL, ''
//'regdate', 'timestamp', 'YES', '', 'CURRENT_TIMESTAMP', 'DEFAULT_GENERATED'
//'view_cnt', 'int', 'YES', '', '0', ''
