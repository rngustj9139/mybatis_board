package koo.mybatis_study_project.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RequestBoardDto {

    private String boardWriter;
    private String boardPass; // 게시글 비밀번호
    private String boardTitle;
    private String boardContents;

}
