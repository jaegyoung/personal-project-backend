package kr.eddi.demo.board.repository;

import kr.eddi.demo.board.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findByWriter(String writer);

}
