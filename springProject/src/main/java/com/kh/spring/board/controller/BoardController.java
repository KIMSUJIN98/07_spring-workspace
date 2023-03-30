package com.kh.spring.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.board.model.service.BoardServiceImpl;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
public class BoardController {
	
	@Autowired
	private BoardServiceImpl bService; // 편의상 BoardService가 아닌 BoardServiceImpl
	
	// 메뉴바 클릭시 				/list.bo					(기본적으로 1번 페이지 요청)
	// 페이징바 클릭시				/list.bo?cpage=요청하는 페이지수	
	
	@RequestMapping("list.bo")
	public String selectList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) { // value가 "cpage"인 것을 뽑아서 int currentPage 변수에 셋팅. "cpage" 값이 없는 경우 기본값 "1"
		//System.out.println(currentPage);
		
		int listCount = bService.selectListCount();
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 10, 5);
		
		ArrayList<Board> list = bService.selectList(pi);
		
		model.addAttribute("pi", pi); // Model 객체는 requestScope
		model.addAttribute("list", list);
		
		return "board/boardListView";
		
		
		
	}
	
}
