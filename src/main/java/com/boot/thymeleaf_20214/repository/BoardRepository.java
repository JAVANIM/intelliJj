package com.boot.thymeleaf_20214.repository;

import com.boot.thymeleaf_20214.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BoardRepository extends JpaRepository<Board,Long> {
//검색을 위한 제목 내용 페이징

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
