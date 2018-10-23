package kr.or.ddit.util.model;

public class StringUtil {
	public static String getFileNameFromHeader(String contentDispostion){
		String fileName="";
		String[] splits = contentDispostion.split("; ");
		for(String str:splits){
			if(str.indexOf("filename=")>=0){
				fileName=str.substring(10, str.lastIndexOf("\""));
			}
		}
		return fileName;
	}

	public static String getCookie(String cookieString, String key) {
		String[] cookies = cookieString.split("; ");
		
		String cookieValue="";
		for(String str: cookies){
			System.out.println("str : "+ str);
			
			if(str.startsWith(key+"=")){
				cookieValue = str.substring((key+"=").length());
			}
		}

		return cookieValue;
	}
	
	
}
