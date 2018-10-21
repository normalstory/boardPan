package kr.or.ddit.board.web.boardPan;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.board.model.BoardTextVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceInf;

@WebServlet("/boardTextEditer")
public class BoardTextEditer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" wellcom in the 'doGet' Method on boardTextEditer");

		String userId = request.getParameter("userId");
		System.out.println("userId : "+userId);
		request.setAttribute("userId", userId);
		
		BoardServiceInf boardService = new BoardService();
		String panId = request.getParameter("panId");
		System.out.println("panId : "+panId);
		BoardPanVo panVo = boardService.chackPan(panId);
		request.setAttribute("panVo", panVo);	
		
		request.getRequestDispatcher("/board/boardTextEditer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" wellcom in the 'doPost' Method on boardTextEditer");
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");	//효율적이 않아보이는데... 맞나? 
		System.out.println("userId : "+userId);

		BoardServiceInf boardService = new BoardService();
		String panId = request.getParameter("panId");
		System.out.println("panId : "+panId);
		
		BoardTextVo textVo = new BoardTextVo();
		textVo.setPanId(request.getParameter("panId"));
		textVo.setTextName(request.getParameter("textName"));
		textVo.setTextSub(request.getParameter("smarteditor"));
		textVo.setTextWriterId(userId);
		textVo.setTextDel("n");
		
		int resultInsert = boardService.insertText(textVo);
		System.out.println("게시글 추가 = 성공:1, 실패:0  = " + resultInsert);

		request.getRequestDispatcher("/main.jsp").forward(request, response);
		
		//sendRedirect 용 
		//BoardPanVo panVo = boardService.chackPan(panId);
		//response.sendRedirect("/board/boardTextList.jsp?page=1&pageSize=10&panName="+panName+"&panId="+panId+"\"");
	}

}
