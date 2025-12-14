package koo.mybatis_study_project.api;

import jakarta.validation.Valid;
import koo.mybatis_study_project.dto.request.RequestBoardDto;
import koo.mybatis_study_project.dto.response.ResponseBoardDto;
import koo.mybatis_study_project.entity.Board;
import koo.mybatis_study_project.service.BoardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController // @Controller + @ResponseBody
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/api/boards")
    public List<ResponseBoardDto> getBoards() {
        List<Board> boards = boardService.boardList(); // ctrl + alt + v

        List<ResponseBoardDto> collect = boards.stream()
                .map(b -> new ResponseBoardDto(b.getId(), b.getBoardWriter(), b.getBoardTitle(), b.getBoardContents(), b.getBoardHits(), b.getCreatedAt()))
                .collect(Collectors.toList());

        return collect;
    }

    @PostMapping("/api/boards/new")
    public CreateBoardResponse createBoard(@RequestBody @Valid RequestBoardDto requestBoardDto) {
        Board board = new Board();

        board.setBoardWriter(requestBoardDto.getBoardWriter());
        board.setBoardTitle(requestBoardDto.getBoardTitle());
        board.setBoardContents(requestBoardDto.getBoardContents());
        board.setBoardPass(requestBoardDto.getBoardPass());

        Board findBoardByTitle = boardService.boardCreate(board);

        return new CreateBoardResponse(findBoardByTitle.getId());
    }

    @Data // @Getter + @Setter + @ToString + @RequiredArgsConstructor
    static class CreateBoardResponse { // Nested Class

        private Long id;

        public CreateBoardResponse(Long id) {
            this.id = id;
        }

    }

}
