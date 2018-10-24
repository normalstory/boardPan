package kr.or.ddit.board.web.boardPan;

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

@MultipartConfig(maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5) //=(5M, 5개)
@WebServlet(urlPatterns={"/boardTextEditer", "/boardTextReplyEditer"})
public class BoardTextEditer extends HttpServlet {
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
		}
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
		//System.out.println("textVo.getSeqNum() : "+textVo.getSeqNum());
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
		//게시글 저장(공통)
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");	//효율적이 않아보이는데... 맞나? 
		System.out.println("userId : "+userId);

		String panId = request.getParameter("panId");
		System.out.println("panId : "+panId);
		
		String textNumP = request.getParameter("textNumP");	 
		System.out.println("textNumP : "+textNumP);
		String seqNum = request.getParameter("seqNum");	 
		System.out.println("seqNum : "+seqNum);
		//if(textNumP.equals(SeqNum)) {}
		
		BoardServiceInf boardService = new BoardService();
		
		BoardTextVo textVo = new BoardTextVo();
		textVo.setPanId(request.getParameter("panId"));
		//textVo.setTextName(request.getParameter("textName"));
		textVo.setTextSub(request.getParameter("smarteditor"));
		textVo.setTextWriterId(userId);
		textVo.setTextDel("n");
		String textName = request.getParameter("textName");
		if(seqNum!=null) {
			textVo.setSeqNum(Integer.parseInt(seqNum));
		}
		if(textNumP!=null) {
			textVo.setTextNumP(Integer.parseInt(textNumP));
		}
		
		textVo.setTextName(textName);
		System.out.println("textVo : "+textVo);
		//저장sql 
		int resultInsert = boardService.insertText(textVo);
		System.out.println("게시글만 추가 = 성공:1, 실패:0  = " + resultInsert);
		
		// *** 다중 첨부파일 
		int fileAddCnt = Integer.parseInt(request.getParameter("addDirBtCnt"));
		System.out.println("fileAddCnt : "+fileAddCnt);
		
		for(int count =1; count<fileAddCnt+1; count++) {
			String fileParamName = "uploadFile"+count;
			System.out.println("fileParamName : "+fileParamName);
			//읽어오기
			try {
				Part profilePart = request.getPart(fileParamName);	
				System.out.println("**파일속성 : "+ profilePart.getContentType());
				//파일과 관련된 부가 정보 
				System.out.println("**저장 폴더명, 파일명.확장자 : " + profilePart.getHeader("Content-disposition"));

				//1
				String contentDispostion = profilePart.getHeader("Content-disposition");
				String fileName = StringUtil.getFileNameFromHeader(contentDispostion);
				System.out.println("fileName :"+ fileName);
				
				//파일 쓰기 방식-1
				String fileLink = "http://"+request.getServerName()+":"+request.getServerPort()+"/uploadFile/"+fileName;
				System.out.println("fileLink : "+fileLink);
				String saveLink = "/Users/bhuanchanwoo/git/boardPan/src/main/webapp/uploadFile/"+fileName;
				System.out.println("saveLink : "+saveLink);
				profilePart.write(saveLink);
				profilePart.delete();	

				BoardAddFileVo uploadFile =  new BoardAddFileVo();
				uploadFile.setAddFileUrl(fileLink);
				uploadFile.setAddFileName(fileName);
				int fileAddResult = boardService.addFile(fileLink);
				System.out.println(count+"개의 첨부 파일 추가 = 성공:1, 실패:0  = " + fileAddResult);

				//서버에 전달 
				List<BoardAddFileVo> addFilesList = boardService.addFilesList(textVo.getTextNum());
				request.setAttribute("addFilesList", addFilesList);
				System.out.println("addFilesList : "+addFilesList);
			} catch (IOException e) {
				System.out.println("첨부된 파일이 없습니다.");
			}	
		}
		
		
		
		//request.getRequestDispatcher("/main.jsp").forward(request, response);
		response.sendRedirect("boardTextList?page=1&pageSize=10&panId="+panId);
		
		//sendRedirect 용 
		//BoardPanVo panVo = boardService.chackPan(panId);
		//response.sendRedirect("/board/boardTextList.jsp?page=1&pageSize=10&panName="+panName+"&panId="+panId+"\"");
	}

}
