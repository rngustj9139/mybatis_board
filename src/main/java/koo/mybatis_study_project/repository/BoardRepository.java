package koo.mybatis_study_project.repository;

import koo.mybatis_study_project.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    public void save(BoardDto boardDto) {
        sqlSessionTemplate.insert("Board.save", boardDto); // Board는 board-mapper.xml의 Board를 의미, save는 쿼리문을 담고 있는 태그를 의미 (board-mapper.xml에 작성)
    }

}
