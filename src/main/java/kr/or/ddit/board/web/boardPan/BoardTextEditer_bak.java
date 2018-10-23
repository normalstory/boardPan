package kr.or.ddit.board.web.boardPan;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.board.model.BoardAddFileVo;
import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.board.model.BoardTextVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.util.model.StringUtil;

//@MultipartConfig(maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5) //=(5M, 5개)
//@WebServlet(urlPatterns={"/boardTextEditer", "/boardTextReplyEditer", "/addFile"})
public class BoardTextEditer_bak extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" wellcom in the 'doGet' Method on boardTextEditer");
		//요청 URL로 로직 분기 
		String uri = request.getRequestURI();
		System.out.println("doGet() on UserServlet :"+uri);

		//사용자 전체 조회
		if(uri.equals("/boardTextEditer")){
			boardTextEditer(request, response);
		//사용자 페이징 조회
		}else if(uri.equals("/boardTextReplyEditer")){
			boardTextReplyEditer(request, response);
		//사용자 페이징 조회
		}else if(uri.equals("/addFile")){
			addFile(request, response);
		}
	}

	private void addFile(HttpServletRequest request, HttpServletResponse response) {
		
	}

	private void boardTextReplyEditer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//답글작성
		BoardServiceInf boardService = new BoardService();
		String userId = request.getParameter("userId");
		System.out.println("userId : "+userId);
		request.setAttribute("userId", userId);
		
		String textNum = request.getParameter("textNum");
		System.out.println("textNum : "+textNum);
		BoardTextVo textVo = boardService.textVoDetail(Integer.parseInt(textNum));
		request.setAttribute("textVo", textVo);
		
		String panId = request.getParameter("panId");
		System.out.println("panId : "+panId);
		BoardPanVo panVo = boardService.chackPan(panId);
		request.setAttribute("panVo", panVo);	
		
		request.getRequestDispatcher("/board/boardTextReplyEditer.jsp").forward(request, response);
	}

	private void boardTextEditer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//새글작성
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
		
		String textNumP = request.getParameter("textNumP");	 
		System.out.println("textNumP : "+textNumP);

		BoardServiceInf boardService = new BoardService();
		String panId = request.getParameter("panId");
		System.out.println("panId : "+panId);
		
		BoardTextVo textVo = new BoardTextVo();
		textVo.setPanId(request.getParameter("panId"));
		//textVo.setTextName(request.getParameter("textName"));
		textVo.setTextSub(request.getParameter("smarteditor"));
		textVo.setTextWriterId(userId);
		textVo.setTextDel("n");
		String textName = request.getParameter("textName");
		if(textNumP!=null) {
			textVo.setTextNumP(Integer.parseInt(textNumP));
			
			StringBuilder sb = new StringBuilder("&#8618;");
			sb.append("&nbsp;");
			sb.insert(0,"&nbsp;");
			textName = sb+textName;
		}
		textVo.setTextName(textName);
		System.out.println("textVo : "+textVo);
		int resultInsert = boardService.insertText(textVo);
		System.out.println("게시글 추가 = 성공:1, 실패:0  = " + resultInsert);


		// *** 첨부파일 
		//읽어오기
		Part profilePart = request.getPart("uploadFile");
		System.out.println("원본 uploadFile  : "+request.getParameter("uploadFile"));
		System.out.println("profilePart : "+ profilePart);
		System.out.println("profilePart.getContentType() : "+ request.getPart("uploadFile"));
		//파일과 관련된 부가 정보 
		System.out.println("Content-disposition : " + profilePart.getHeader("Content-disposition"));
		String contentDispostion = profilePart.getHeader("Content-disposition");
		//별도 class로 리팩토링
		String fileName = StringUtil.getFileNameFromHeader(contentDispostion);
		String saveUrl = "/Users/bhuanchanwoo/git/boardPan/temp/uploadFile";
		String fileLink = saveUrl+"/"+fileName;
		//파일 쓰기 방식-1
		profilePart.write(fileLink);
		profilePart.delete();	// 파일 업로드 과정에서 사용한 디스크 임시영역 부분을 삭제해줌
		//파일 쓰기 방식-2 <-- 이상한 주소?가 뜨지?
		//				String path = getServletContext().getRealPath("/uploadFile"); //url정보를 파일경로로 변경해주는 역할 수행
		//				System.out.println("name : "+path+ File.separator + fileName);
		//				/Users/bhuanchanwoo/workspace_JSP_201810/.metadata/.plugins/
		//				org.eclipse.wst.server.core/tmp0/wtpwebapps/boardPan/profile/pic.png
		//				profilePart.write(path + File.separator + fileName);
		if(fileLink!=null) {
			BoardAddFileVo uploadFile =  new BoardAddFileVo();
			uploadFile.setAddFileUrl(fileLink);
			uploadFile.setAddFileName(fileName);
			int fileAddResult = boardService.addFile(fileLink);
			System.out.println("게시글 추가 = 성공:1, 실패:0  = " + fileAddResult);
			
			//List<BoardAddFileVo> addFilesList = boardService.addFilesList();
			//request.setAttribute("addFilesList", addFilesList);
		}
		
		request.getRequestDispatcher("/main.jsp").forward(request, response);
		
		//sendRedirect 용 
		//BoardPanVo panVo = boardService.chackPan(panId);
		//response.sendRedirect("/board/boardTextList.jsp?page=1&pageSize=10&panName="+panName+"&panId="+panId+"\"");
	}

}
