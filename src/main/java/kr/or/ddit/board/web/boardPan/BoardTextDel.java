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

@WebServlet("/boardTextDel")
public class BoardTextDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardServiceInf boardService = new BoardService();
		BoardTextVo textVo = boardService.textVoDetail(Integer.parseInt(request.getParameter("textNum")));
		textVo.setTextDel("y");
		
		int resultTextDelUpdate = boardService.textDelUpdate(textVo);
		System.out.println("게시글 추가 = 성공:1, 실패:0  : " + resultTextDelUpdate);

		request.getRequestDispatcher("/main.jsp").forward(request, response);
		
		//sendRedirect 용 
//		BoardPanVo panVo = boardService.chackPan(request.getParameter("panId"));
//		request.setAttribute("panVo", panVo);	
//		panVo.getPanId();
//		panVo.getPanName();
		//response.sendRedirect("/board/boardTextList.jsp?page=1&pageSize=10&panName="+panVo.getPanId()+"&panId="+getPanName()+"\"");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
