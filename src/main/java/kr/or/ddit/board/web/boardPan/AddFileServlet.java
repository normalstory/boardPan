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
import kr.or.ddit.util.model.StringUtil;

@MultipartConfig(maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5) //=(5M, 5개)
@WebServlet("/addFile")
public class AddFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" wellcom --addFile-- in the 'doGet' Method on boardTextEditer");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" wellcom --addFile-- in the 'doPost' Method on boardTextEditer");
		//읽어오기
		String userId = request.getParameter("userId");
		String panId = request.getParameter("panId");
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
		System.out.println("최종 fileLink : "+ fileLink);
		
		//파일 쓰기 방식-1
		profilePart.write(fileLink);
		profilePart.delete();	// 파일 업로드 과정에서 사용한 디스크 임시영역 부분을 삭제해줌
		

		int addFileCnt=1;
		if(addFileCnt<6) {
			BoardAddFileVo uploadFile =  new BoardAddFileVo();
			uploadFile.setAddFileUrl(fileLink);
			uploadFile.setAddFileName(fileName);
			
			request.setAttribute("uploadFile",uploadFile);
			request.setAttribute("addFileCnt",addFileCnt);
			addFileCnt++;
		}else {
			System.out.println("5개까지만 파일첨부가 가능합니다");
		}
		request.getRequestDispatcher("/board/boardTextEditer.jsp?userId="+userId+"+&panId="+panId).forward(request, response);


		// *** 첨부파일 
//		BoardServiceInf boardService = new BoardService();
		//int fileAddResult = boardService.addFile(fileLink);
		//System.out.println("게시글 추가 = 성공:1, 실패:0  = " + fileAddResult);
		
		//List<BoardAddFileVo> addFilesList = boardService.addFilesList();
		//request.setAttribute("addFilesList", addFilesList);
		//response.sendRedirect("/boardTextEditer");
	}

}
