package koo.mybatis_study_project.controller;

import koo.mybatis_study_project.dto.request.RequestBoardDto;
import koo.mybatis_study_project.dto.response.ResponseBoardDto;
import koo.mybatis_study_project.entity.Board;
import koo.mybatis_study_project.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    /**
     * Rest API와 MVC 패턴의 URL 작성법은 다르다.
     * Rest API의 URL의 경우 URL이 같더라도 HTTP 메서드 매핑 방식의 차이로 인해 문제가 발생하지 않는다 (e.g. @GetMapping("/api/boards"), @PostMapping("/api/boards"))
     * MVC의 URL의 경우 URL을 통해 웹 페이지를 먼저 보여주고, form의 method가 POST인 것을 제외하면 거의 @GetMapping 어노테이션을 사용한다. (e.g. 하기 HTTP 메서드 어노테이션 참고)
     */
    @GetMapping("/boards/new")
    public String boardCreateForm() {
        return "boards/boardCreateForm";
    }

    /**
     *
     */
    @PostMapping("/boards/new")
    public String boardCreate(@ModelAttribute RequestBoardDto boardDto) {
        log.info("boardDto = {}", boardDto);

        Board board = new Board();
        board.setBoardWriter(boardDto.getBoardWriter());
        board.setBoardTitle(boardDto.getBoardTitle());
        board.setBoardPass(boardDto.getBoardPass());
        board.setBoardContents(boardDto.getBoardContents());
        boardService.boardCreate(board);

        return "redirect:/";
    }

    @GetMapping("/boards")
    public String boardList(Model model) {
        List<Board> boardList = boardService.boardList();

        List<ResponseBoardDto> collect = boardList.stream()
                        .map(b -> new ResponseBoardDto(b.getId(), b.getBoardWriter(), b.getBoardTitle(), b.getBoardContents(), b.getBoardHits(), b.getCreatedAt()))
                        .collect(Collectors.toList());

        log.info("List<ResponseBoardDto> = {}", collect);
        model.addAttribute("boardList", collect);

        return "boards/boardList";
    }

    @GetMapping("/boards/{id}")
    public String boardOneDetail(@PathVariable("id") Long id, Model model) {
        // 조회수 증가 처리
        boardService.updateHits(id);
        // 상세내용 가져오기
        Board board = boardService.findById(id);

        ResponseBoardDto responseBoardDto = new ResponseBoardDto();
        responseBoardDto.setId(board.getId());
        responseBoardDto.setBoardWriter(board.getBoardWriter());
        responseBoardDto.setBoardTitle(board.getBoardTitle());
        responseBoardDto.setBoardContents(board.getBoardContents());
        responseBoardDto.setBoardHits(board.getBoardHits());
        responseBoardDto.setCreatedAt(board.getCreatedAt());

        log.info("responseBoardDto = {}", responseBoardDto);
        model.addAttribute("board", responseBoardDto);

        return "boards/boardOneDetail";
    }

    /**
     *
     */
    @GetMapping("/boards/{id}/edit") // 게시글 일부 수정
    public String boardEdit(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findById(id);
        // ResponseBoardDto에 @Builder 어노테이션 적용? (빌더패턴)

        return "boards/boardEditForm";
    }

}
