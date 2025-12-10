package koo.mybatis_study_project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BoardDto {

    private Long id;
    private String boardWriter;
    private String boardPass; // 게시글 비밀번호
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;

}
