package koo.mybatis_study_project.repository;

import koo.mybatis_study_project.entity.Board;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    public void boardCreate(Board board) {
        // 테이블 생성 필요 (board-mapper.xml 파일의 주석 참고, 테이블에 데이터 insert)
        sqlSessionTemplate.insert("Board.create", board); // Board는 board-mapper.xml의 Board를 의미, create는 쿼리문을 담고 있는 태그를 의미 (board-mapper.xml에 작성)
    }

    public List<Board> boardList() {
        return sqlSessionTemplate.selectList("Board.list");
    }

    public Board findById(Long id) {
        return sqlSessionTemplate.selectOne("Board.findById", id);
    }

    public Board findByTitle(String boardTitle) {
        return sqlSessionTemplate.selectOne("Board.findByTitle", boardTitle);
    }

}
