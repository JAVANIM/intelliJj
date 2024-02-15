package com.boot.thymeleaf_20214.controller;

import com.boot.thymeleaf_20214.model.Board;
import com.boot.thymeleaf_20214.repository.BoardRepository;
import com.boot.thymeleaf_20214.validator.BoardValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;
    @GetMapping("/list")
//    public String list(Model model){
//    public String list(Model model){
//    public String list(Model model, Pageable pageable){
//    public String list(Model model,@PageableDefault(size = 1) Pageable pageable){
    public String list(Model model,@PageableDefault(size = 20) Pageable pageable,@RequestParam(required = false,defaultValue = "") String searchText){
        log.info("@##$@ list()");
        
        
        // 최신글부터
        Sort sort = Sort.by(Sort.Order.desc("id"));

        // Pageable에 정렬 조건 추가
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);

//        List<Board> boards = boardRepository.findAll();
//        Page<Board> boards = boardRepository.findAll(PageRequest.of(1, 20));
//        Page<Board> boards = boardRepository.findAll(PageRequest.of(0, 20));
//        Page<Board> boards = boardRepository.findAll(PageRequest.of(0, 20));
//        Page<Board> boards = boardRepository.findAll(pageable);
//        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText,searchText,pageable);
        int startPage = 1;
        int endPage = boards.getTotalPages();




        model.addAttribute("boards",boards);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);


        return "board/list";
    }

    //    get매핑
    @GetMapping("/form")
//    public String form(Model model, @RequestParam Long id){
    public String form(Model model, @RequestParam(required = false) Long id){
        log.info("@##$@ get form()");
        if (id == null){
            model.addAttribute("board",new Board());
        } else { //수정
//          Board board = boardRepository.findById(id);
//            Optional<Board> board = boardRepository.findById(id);
          Board board = boardRepository.findById(id).orElse(null);
          model.addAttribute("board",board);

        }

        return "/board/form";
    }

    @PostMapping("/form")
//    public String form(@ModelAttribute Board board, Model model){
    public String form(@Valid Board board, BindingResult bindingResult){
      log.info("@##$@ post form()");
        boardValidator.validate(board,bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }

      boardRepository.save(board);
      return "redirect:/board/list";
    }

}
