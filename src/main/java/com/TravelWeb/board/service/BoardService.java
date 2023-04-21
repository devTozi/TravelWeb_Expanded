package com.TravelWeb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TravelWeb.board.model.BoardVO;
import com.TravelWeb.board.model.CommentVO;

public interface BoardService {
	
	void regist(HttpServletRequest request, HttpServletResponse response); //등록
	ArrayList<BoardVO> getList(HttpServletRequest request, HttpServletResponse response); //조회
	BoardVO getContent(HttpServletRequest request, HttpServletResponse response); //상세내용 확인
	void update(HttpServletRequest request, HttpServletResponse response);//정보수정
	int delete(HttpServletRequest request, HttpServletResponse response); // 삭제
	void like_insert(HttpServletRequest request, HttpServletResponse response); // 좋아요 추가
	void like_delete(HttpServletRequest request, HttpServletResponse response); // 좋아요 삭제
	boolean like_check(HttpServletRequest request, HttpServletResponse response); // 좋아요 체크
	ArrayList<BoardVO> getMyList(HttpServletRequest request, HttpServletResponse response); //조회
	ArrayList<BoardVO> getLikeList(HttpServletRequest request, HttpServletResponse response); //조회
	ArrayList<BoardVO> getList_order(HttpServletRequest request, HttpServletResponse response); // 정렬
	void addComment(HttpServletRequest request, HttpServletResponse response);
	ArrayList<CommentVO> getComments(HttpServletRequest request, HttpServletResponse response);  
}
