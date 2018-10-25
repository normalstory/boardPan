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

@WebServlet("/boardPanManager")
public class BoardPanManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*****  Here is Servlet on BoardManager by doGet");

		BoardServiceInf boardService = new BoardService();

		List<BoardPanVo> boardList = boardService.panList();
		System.out.println("(Servlet) boardList : " + boardList);
		
		request.getServletContext().setAttribute("boardList", boardList);	
		request.getRequestDispatcher("/board/boardPanManager.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Here is Servlet on BoardManager by doPost");
		request.setCharacterEncoding("UTF-8");
		
		BoardServiceInf boardService = new BoardService();
		
		String pan_Case = request.getParameter("pan_Case");
		//String pan_Name = request.getParameter("pan_Name");
		//String userId = request.getParameter("userId");
		//String pan_Del = request.getParameter("pan_Del");
		//String panId = request.getParameter("panId");

		System.out.println("pan_Case : " + pan_Case);
		//System.out.println("Pan_Name : "+ pan_Name);
		//System.out.println("login userId : " + userId);
		//System.out.println("Pan_Del : " + pan_Del);
		//System.out.println("panId : " + panId);
		
		
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
			
			
			//다중처리 
			String[] panIds = request.getParameterValues("pan_Id");
			System.out.println(" 다중 게시판 출력 여부들  : " + panIds.length);
			System.out.println(" 0번 방 게시판 id  : " + panIds[0]);
			String[] names = request.getParameterValues("pan_Name");
			System.out.println(" 다중 게시판 이름들  : " + names.length);
			System.out.println(" 0번 방 게시판 이름  : " + names[0]);
			String[] dels = request.getParameterValues("boardUse");
			System.out.println(" 다중 게시판 출력 여부들  : " + dels.length);
			System.out.println(" 0번 방 게시판 출력 여 : " + dels[0]);
			
			//for(String name : names) {
			for(int i=0;i<names.length-1;i++) {	
				System.out.println("panIds[i] : "+panIds[i]);
				BoardPanVo panVo = boardService.chackPan(panIds[i]);
				System.out.println("업데이트되는 panVo 상세 : "+ panVo);
				panVo.setPanName(names[i]);
				panVo.setPanDel(dels[i]);
				panVo.setPanNum(i);
				
				int updatePanResult = boardService.updatePan(panVo);
				System.out.println(i+ ". 게시판관리 > panVo 수정: 성공1,실패0 : "+ updatePanResult);
				request.getServletContext().setAttribute("panVo", panVo);
			}
		}
		
		
		//게시판 목록 새로고침용					// <-- 나중에 메서드로 바꿀 필요있음
		//doGet(request,response);
		boardService = new BoardService();	// <-- 될줄 알았는데 안됨 : Cannot forward after response has been committed
		List<BoardPanVo> panListManu = boardService.panListManu();
		request.getServletContext().setAttribute("panListManu", panListManu);
		
		//request.getRequestDispatcher("/board/boardPanManager.jsp").forward(request, response);	// <- 출력은 되는데 왼쪽 메뉴와 달리 실시간 반영이 안됨 
		response.sendRedirect("/boardPanManager");	//<--데이터베이스 연동 시 사용(jsp가 아닌 sevlet으로) 
	}
}

