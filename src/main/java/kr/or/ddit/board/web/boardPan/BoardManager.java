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
		
		BoardServiceInf boardService = new BoardService();
		String pan_Name = request.getParameter("pan_Name");
		String userId = request.getParameter("userId");
		String pan_Del = request.getParameter("pan_Del");
		
		System.out.println("addPan_Name : "+ pan_Name);
		System.out.println("panWriter : " + userId);
		System.out.println("addPan_Del : " + pan_Del);
		
		BoardPanVo panVo = null;
		
		panVo = boardService.chackPan(userId);
		System.out.println("panVo : "+panVo);
		
		
		if(panVo==null){
			System.out.println("서비스 추가 ");
			panVo = new BoardPanVo();
			panVo.setPanName(request.getParameter("pan_Name"));
			panVo.setPanWriter(request.getParameter("userId"));
			panVo.setPanDel(request.getParameter("pan_Del"));
			System.out.println("insert panVo : "+ panVo);
			
			int insertPanResult = boardService.insertPan(panVo);
			System.out.println(" 게시판관리 > panVo 추가: 성공1,실패0 : "+ insertPanResult);	
			
			request.setAttribute("panVo", panVo);
		}else{
			System.out.println("서비스 수정 ");
			panVo.setPanName(request.getParameter("pan_Name"));
			panVo.setPanWriter(request.getParameter("userId"));
			panVo.setPanDel(request.getParameter("pan_Del"));
			System.out.println("upadate panVo : "+ panVo);
			
			int updatePanResult = boardService.updatePan(panVo);
			System.out.println(" 게시판관리 > panVo 수정: 성공1,실패0 : "+ updatePanResult);
			
			request.setAttribute("panVo", panVo);
		}
		
		
		
		
		
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
}
