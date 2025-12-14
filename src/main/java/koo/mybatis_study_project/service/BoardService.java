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

    public Board boardCreate(Board board) {
        validateDuplicateBoardTitle(board); // 학습용으로 제작, 원래 게시글의 제목은 중복되어도 딱히 상관은 없음
        boardRepository.boardCreate(board);
        String boardTitle = board.getBoardTitle();
        Board findBoardByTitle = boardRepository.findByTitle(boardTitle);

        return findBoardByTitle;
    }

    private void validateDuplicateBoardTitle(Board board) { // 학습용으로 제작, 원래 게시글의 제목은 중복되어도 딱히 상관은 없음
        List<Board> boards = boardRepository.boardList();

        boards.stream()
                .filter(b -> b.getBoardTitle().equals(board.getBoardTitle()))
                .findFirst()
                .ifPresent(b -> {
                    throw new IllegalStateException("이미 존재하는 게시글의 제목 입니다."); // 예외 (Exception 발생)
                });

        /**
         * 추가 예정?
         * 학습용, 원래 id는 auto_increment이므로 중복될 일은 없음
         * Optional에서 orElseThrow란 값이 있으면 반환하고 없으면 예외 (Exception을 반환)
         */
//        List<Board> findedBoardsById = boardRepository.findById(board.getId());
//        
//        if (!findedBoardsById.isEmpty()) {
//            throw new IllegalStateException("이미 존재하는 게시글의 id 입니다.");
//        }
    }

    public List<Board> boardList() {
        return boardRepository.boardList();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id);
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

}
