package kr.or.ddit.board.web.boardPan;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardTextVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.util.model.PageVo;

@WebServlet("/boardPanList")
public class BoardPanList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" wellcom in the 'doGet' Method on boardPanList");
		String panId = request.getParameter("panId");
		System.out.println(" > "+panId+ " 게시판으로 들어오셨습니다.");
		
		//게시판에 있는 text 목록 객체만들기 
//		BoardServiceInf boardService = new BoardService();
//		List<BoardTextVo> textVoList = boardService.textVoList(request.getParameter("panId"));
//		request.setAttribute("textVoList", textVoList);
		
		//"페이지 설정이 반영된" + 게시판에 있는 text 목록 객체만들기
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		System.out.println("page = "+page+" , pageSize = "+pageSize);

		PageVo pageVo = new PageVo();
		pageVo.setPage(page);
		pageVo.setPageSize(pageSize);
		pageVo.setPanId(panId);
		
		BoardServiceInf boardService = new BoardService();
		//페이지 당 리스트 개수를 제한한 목록 
		Map<String, Object> resultMap = boardService.textVoListPage(pageVo, panId);
		List<BoardTextVo> textVoListPage = (List<BoardTextVo>) resultMap.get("pageVoList");
		int pageNum = (int)resultMap.get("pageNum");
		System.out.println("on Map : textVoListPage : "+textVoListPage);
		System.out.println("on Map : pageNum : "+pageNum);
		
		request.setAttribute("textVoListPage", textVoListPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("panId", panId);	// <-- 편의를 위해 작성은 하는데 최선인지는 모르겠다 
		
		request.getRequestDispatcher("/board/boardPanList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" wellcom in the 'doPost' Method on boardPanList");
	}

}
