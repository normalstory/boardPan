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

//@WebServlet("/boardManager")			//(urlPatterns={"/boardManager","/insertPan"})
public class CopyOfBoardManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*****  Here is Servlet on BoardManager by doGet");

//		//***요청 URL로 로직 분기 
//		String uri = request.getRequestURI();
//		System.out.println("doGet() on UserServlet :"+uri);
//
//		//사용자 전체 조회
//		if(uri.equals("/boardManager")){
//			boardManager(request, response);
//		}else if(uri.equals("/insertPan")){
//			insertPan(request, response);
//		}
		
		BoardServiceInf boardService = new BoardService();

		//게시판 리스트 <- 주석처리하려다가 "다시 품" 
		//질의 : <- 어플리케이션 스코프로 set했어도 새로고침하면 메뉴/사용자 아이디 사라지는 이슈 
		List<BoardPanVo> boardList = boardService.panList();
		System.out.println("(Servlet) boardList : " + boardList);
		
//		request.getServletContext().setAttribute("boardList", boardList);
		request.getSession().setAttribute("boardList", boardList);			
//		request.setAttribute("boardList", boardList) ;
		
		request.getRequestDispatcher("/board/boardManager.jsp").forward(request, response);
	}

	private void boardManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*****  Here is boardManager---Servlet on BoardManager by doGet");
	}

	private void insertPan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*****  Here is insertPan---Servlet on BoardManager by doGet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Here is Servlet on BoardManager by doPost");
		
		
		BoardServiceInf boardService = new BoardService();

		//사용자 아이디 쿼리 넘어오는지 확인 
		System.out.println("addPan_Name : "+request.getParameter("addPan_Name"));
		System.out.println("userId : " +request.getParameter("userId"));
		System.out.println("addPan_Del : " +request.getParameter("addPan_Del"));
		
		BoardPanVo panVo = boardService.chackPan(request.getParameter("userId"));
				
		if(panVo!=null){
			//게시판 수정
			panVo.setPanName(request.getParameter("pan_Name"));
			panVo.setPanWriter(request.getParameter("userId"));
			panVo.setPanDel(request.getParameter("pan_Del"));
			System.out.println("upadate panVo : "+ panVo);
			
			int updatePanResult = boardService.updatePan(panVo);
			System.out.println(" 게시판관리 > panVo 수정: 성공1,실패0 : "+ updatePanResult);

		}else{
			//게시판 등록
			panVo = new BoardPanVo();
			panVo.setPanName(request.getParameter("pan_Name"));
			panVo.setPanWriter(request.getParameter("userId"));
			panVo.setPanDel(request.getParameter("pan_Del"));
			System.out.println("insert panVo : "+ panVo);
			
			int insertPanResult = boardService.insertPan(panVo);
			System.out.println(" 게시판관리 > panVo 추가: 성공1,실패0 : "+ insertPanResult);			
		}
		
		request.setAttribute("panVo", panVo);
		
		
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
}
