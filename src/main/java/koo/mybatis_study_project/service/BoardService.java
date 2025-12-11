package koo.mybatis_study_project.service;

import koo.mybatis_study_project.dto.BoardDto;
import koo.mybatis_study_project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDto boardDto) {
        boardRepository.save(boardDto);
    }

}
