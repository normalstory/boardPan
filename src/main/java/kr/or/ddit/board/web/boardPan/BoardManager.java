package kr.or.ddit.board.web.boardPan;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceInf;

@WebServlet("/boardManager")
public class BoardManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*****  Here is Servlet on BoardManager by doGet");

		BoardServiceInf boardService = new BoardService();

		List<BoardPanVo> boardList = boardService.panList();
		System.out.println("(Servlet) boardList : " + boardList);
		
		request.getServletContext().setAttribute("boardList", boardList);	
		request.getRequestDispatcher("/board/boardManager.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Here is Servlet on BoardManager by doPost");
		request.setCharacterEncoding("UTF-8");
		
		BoardServiceInf boardService = new BoardService();
		
		String pan_Case = request.getParameter("pan_Case");
		String pan_Name = request.getParameter("pan_Name");
		String userId = request.getParameter("userId");
		String pan_Del = request.getParameter("pan_Del");
		String panId = request.getParameter("panId");

		System.out.println("Pan_Del : " + pan_Case);
		System.out.println("Pan_Name : "+ pan_Name);
		System.out.println("login userId : " + userId);
		System.out.println("Pan_Del : " + pan_Del);
		System.out.println("panId : " + panId);
		
		
		if(pan_Case.equals("add")){
			System.out.println("게시판 추가 ");
			BoardPanVo panVo = new BoardPanVo();
			
			panVo.setPanName(request.getParameter("pan_Name"));
			panVo.setPanWriter(request.getParameter("userId"));
			panVo.setPanDel(request.getParameter("pan_Del"));
			System.out.println("insert panVo : "+ panVo);
			
			int insertPanResult = boardService.insertPan(panVo);
			System.out.println(" 게시판관리 > panVo 추가: 성공1,실패0 : "+ insertPanResult);	
			
			request.getServletContext().setAttribute("panVo", panVo);
		}
		
		if(pan_Case.equals("update")){
			System.out.println("게시판 수정 ");
			BoardPanVo panVo = boardService.chackPan(panId);
			System.out.println("upadate panVo : "+ panVo);
			
			panVo.setPanName(request.getParameter("pan_Name"));
			panVo.setPanDel(request.getParameter("pan_Del"));
			
			int updatePanResult = boardService.updatePan(panVo);
			System.out.println(" 게시판관리 > panVo 수정: 성공1,실패0 : "+ updatePanResult);
			request.getServletContext().setAttribute("panVo", panVo);
		}
		
		
		//게시판 목록 새로고침용					// <-- 나중에 메서드로 바꿀 필요있음
		//doGet(request,response);
		boardService = new BoardService();	// <-- 될줄 알았는데 안됨 : Cannot forward after response has been committed
		List<BoardPanVo> panListManu = boardService.panListManu();
		request.getServletContext().setAttribute("panListManu", panListManu);
		
		request.getRequestDispatcher("/board/boardManager.jsp").forward(request, response);
	}
}

