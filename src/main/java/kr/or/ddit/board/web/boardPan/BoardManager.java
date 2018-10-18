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
		System.out.println("Here is Servlet on BoardManager by doGet");
		
		
		BoardServiceInf boardService = new BoardService();
		List<BoardPanVo> boardList = boardService.panList();
		System.out.println("(Servlet) boardList : " + boardList);
		
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher("/board/boardManager.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Here is Servlet on BoardManager by doPost");
	}

}
