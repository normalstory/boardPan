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

@WebServlet("/boardTextEditerUpdate")
public class BoardTextEditerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" wellcom in the 'doGet' Method on boardTextEditerUpdate");
		
		String textNum = request.getParameter("textNum");
		System.out.println("textNum : "+textNum);
		String panId = request.getParameter("panId");
		System.out.println("panId : "+panId);
		
		BoardServiceInf boardService = new BoardService();
		BoardTextVo textVo = boardService.textVoDetail(Integer.parseInt(textNum));
		request.setAttribute("textVo", textVo);		

		BoardPanVo panVo = boardService.chackPan(panId);
		request.setAttribute("panVo", panVo);	
		
		
		request.getRequestDispatcher("/board/boardTextEditerUpdate.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" wellcom in the 'doPost' Method on boardTextEditerUpdate");
		request.setCharacterEncoding("utf-8");
		
		String textNum = request.getParameter("textNum");
		System.out.println("textNum : "+textNum);
		BoardServiceInf boardService = new BoardService();
		BoardTextVo textVo = boardService.textVoDetail(Integer.parseInt(textNum));
		textVo.setTextName(request.getParameter("textName"));
		textVo.setTextSub(request.getParameter("smarteditor"));
		int resultUpdate = boardService.updateText(textVo);
		System.out.println("게시글 추가 = 성공:1, 실패:0  : " + resultUpdate);

		//sendRedirect 용 
		String panId = request.getParameter("panId");
		System.out.println("panId : "+panId);
		BoardPanVo panVo = boardService.chackPan(panId);
		request.setAttribute("panVo", panVo);	
//		panVo.getPanId();
//		panVo.getPanName();
		//response.sendRedirect("/board/boardTextList.jsp?page=1&pageSize=10&panName="+panVo.getPanId()+"&panId="+getPanName()+"\"");
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
}
