<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- get 방식으로  : action="/fileUpload" -> 파일 업로드 페이지 출력 -->

<!-- 파일전송은 post만 지원 -->
<!-- post 방식으로 : action="/fileUpload" -> form 파일 전송 -->
<form action="/fileUploadServlet" method="post" enctype="multipart/form-data">
	<input type="text" name="userId" value="brown"><br/>
	<input type="file" name="profile"><br/>
	<input type="submit" value="전송">
	<%-- application.getRealPath("/profile") --%>
</form>

</body>
</html>