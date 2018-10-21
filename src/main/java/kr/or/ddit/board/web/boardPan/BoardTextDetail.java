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

@WebServlet("/boardTextDetail")
public class BoardTextDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" hello~ This is 'doGet' Method on boardTextDetail");
		
		String textNum = request.getParameter("textNum");
		System.out.println("textNum : "+textNum);
		String panId = request.getParameter("panId");
		System.out.println("panId : "+panId);
		
		BoardServiceInf boardService = new BoardService();
		BoardTextVo textVo = boardService.textVoDetail(Integer.parseInt(textNum));
		request.setAttribute("textVo", textVo);

		BoardPanVo panVo = boardService.chackPan(panId);
		request.setAttribute("panVo", panVo);	

		request.getRequestDispatcher("/board/boardTextDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" hello~ This is 'doPost' Method on boardTextDetail");
	}

}
