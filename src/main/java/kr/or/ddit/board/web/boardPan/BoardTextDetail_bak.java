package kr.or.ddit.board.web.boardPan;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardAddFileVo;
import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.board.model.BoardReplayVo;
import kr.or.ddit.board.model.BoardTextVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceInf;

//@WebServlet("/boardTextDetail")
public class BoardTextDetail_bak extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" hello~ This is 'doGet' Method on boardTextDetail");
		
		String textNum = request.getParameter("textNum");
		System.out.println("textNum : "+textNum);
		String panId = request.getParameter("panId");
		System.out.println("panId : "+panId);
		
		BoardServiceInf boardService = new BoardService();
		//게시판 
		BoardPanVo panVo = boardService.chackPan(panId);
		request.setAttribute("panVo", panVo);	
		//게시글
		BoardTextVo textVo = boardService.textVoDetail(Integer.parseInt(textNum));
		request.setAttribute("textVo", textVo);
		//첨부파일 목록
		List<BoardAddFileVo> addFileList = boardService.addFilesList(Integer.parseInt(textNum));
		request.setAttribute("addFileList", addFileList);
		//댓글
		BoardReplayVo replyVo = new BoardReplayVo();
		replyVo.setReplyDel("n");
		replyVo.setTextNum(Integer.parseInt(textNum));
		List<BoardReplayVo> replyList = boardService.replyList(replyVo);
		System.out.println("replyList : "+replyList);
		request.setAttribute("replyList", replyList);

		request.getRequestDispatcher("/board/boardTextDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" hello~ This is 'doPost' Method on boardTextDetail");
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId" );
		System.out.println(" userId : "+ userId);
		String addReply = request.getParameter("addReply" );
		System.out.println(" addReply : "+ addReply);
		String textNum = request.getParameter("textNum" );
		System.out.println(" textNum : "+ textNum);
		String panId = request.getParameter("panId");
		System.out.println("panId : "+panId);
		String delReply = request.getParameter("delReply");
		System.out.println("delReply : "+delReply);
		
		BoardReplayVo replyVo = new BoardReplayVo();
		replyVo.setReplyDel("n");
		replyVo.setReplySub(addReply);
		replyVo.setReplyerId(userId);
		replyVo.setTextNum(Integer.parseInt(textNum));
		
		BoardServiceInf boardService = new BoardService();
		int addResult = boardService.addReply(replyVo);
		System.out.println("성공:1, 실패:0 -> 결과 : "+addResult);
		List<BoardReplayVo> replyList = boardService.replyList(replyVo);
		System.out.println("replyList : "+replyList);
		request.setAttribute("replyList", replyList);
		
		response.sendRedirect("/boardTextDetail?textNum="+textNum+"&panId="+panId+"\"");
	}

}
