package koo.mybatis_study_project.controller;

import koo.mybatis_study_project.dto.request.RequestBoardDto;
import koo.mybatis_study_project.dto.response.ResponseBoardDto;
import koo.mybatis_study_project.entity.Board;
import koo.mybatis_study_project.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards/new")
    public String boardCreateForm() {
        return "boards/boardCreateForm";
    }

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

        model.addAttribute("boardList", collect);

        return "boards/boardList";
    }

}
