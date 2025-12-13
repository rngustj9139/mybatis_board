package koo.mybatis_study_project.service;

import koo.mybatis_study_project.entity.Board;
import koo.mybatis_study_project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void boardCreate(Board board) {
        boardRepository.boardCreate(board);
    }

    public List<Board> boardList() {
        return boardRepository.boardList();
    }

}
