package koo.mybatis_study_project.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Rest API를 사용한다면 하기 어노테이션 모음 사용
 * @Entity
 * @DynamicUpdate
 * @NoArgsConstructor
 * @AllArgsConstructor
 * @Builder
 * @Getter @Setter
 */
@Getter @Setter @ToString
public class Board {

    private Long id;
    private String boardWriter;
    private String boardPass; // 게시글 비밀번호
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;

}
