package kr.or.ddit.util.model.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5) //=(5M, 5개)
@WebServlet("/fileUpload")
public class FileUploadServletabc extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	//fileUpload.jsp 페이지 전달
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/fileUpload/fileUpload.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getReader()
		
		//일반 txt 파라미터 : 기존방식 request.getParameter("파라미터")
		//file 파라미터 : 기존방식 request.getPart("파라미터")
		
		System.out.println("request.getContentType() : "+ request.getContentType());
		System.out.println("userId : "+ request.getParameter("userId"));
		System.out.println("profile : "+ request.getParameter("profile"));

		Part profilePart = request.getPart("profile");
		System.out.println("profilePart.getContentType() : "+ profilePart.getContentType());
		
		//파일과 관련된 부가 정보 
		System.out.println("Content-disposition : " + profilePart.getHeader("Content-disposition"));
		
		String contentDispostion = profilePart.getHeader("Content-disposition");
		String fileName="";
		String[] splits = contentDispostion.split("; ");
		for(String str:splits){
			if(str.indexOf("filename=")>=0){
				fileName=str.substring(10, str.lastIndexOf("\""));
			}
		}
		
		//파일 쓰기 
		profilePart.write("/Users/bhuanchanwoo/git/boardPan/temp/uploadFile"+fileName);
		
		//		profilePart.write("D:\\A_TeachingMaterial\\6.JspSrpgin\\upload\\"
//				+ new String(fileName.getBytes(),"UTF-8"));
//		profilePart.write("D:\\A_TeachingMaterial\\6.JspSrpgin\\upload\\"+fileName);
		profilePart.delete();	//파일 업로드 과정에서 사용한 디스크 임시영역 부분을 삭제해줌
	}
	
}
