package koo.mybatis_study_project.controller;

import koo.mybatis_study_project.dto.BoardDto;
import koo.mybatis_study_project.entity.Board;
import koo.mybatis_study_project.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String boardCreate(@ModelAttribute BoardDto boardDto) {
        log.info("boardDto = {}", boardDto);

        Board board = new Board();
        board.setBoardWriter(boardDto.getBoardWriter());
        board.setBoardTitle(boardDto.getBoardTitle());
        board.setBoardTitle(boardDto.getBoardTitle());
        board.setBoardContents(boardDto.getBoardContents());

        boardService.boardCreate(board);

        return "index";
    }



}
