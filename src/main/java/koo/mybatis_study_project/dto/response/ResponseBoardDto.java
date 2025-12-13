package koo.mybatis_study_project.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ResponseBoardDto {

    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;

    public ResponseBoardDto(Long id, String boardWriter, String boardTitle, String boardContents, int boardHits, String createdAt) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.boardHits = boardHits;
        this.createdAt = createdAt;
    }

}
