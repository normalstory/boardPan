<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="kr.or.ddit.board.model.BoardPanVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>boardManager</title>
<style type="text/css">
select {
	height: 26px;
}
</style>
<!-- basicLib -->
<%@ include file="/common/basicLib.jsp"%>
</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@ include file="/common/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="blog-post">
					<h2 class="blog-post-title">BoardManager</h2>
					<p>게시판을 추가하거나 활성/비활성 여부를 관리하는 페이지 입니다.</p>
					<hr>

					<h3>게시판 목록</h3>
					<p>신규 생성하기</p>
					<form action="#" method="post" name="addBoardPan">
						<ul>
							<li>게시판 이름 : 
								<input type="text" name="addPanName" placeholder="생성할 게시판 이름 기재~" /> 
								<select name="boardUse">
										<option value="x">사용</option>
										<option value="y">안사용</option>
								</select> 
								<input type="submit" name="addPanBt" value="등록">
							</li>
						</ul>
					</form>
					<p>운영 게시판 목록</p>
					<form action="#" method="post" name="boardPanList">
						<ul>
						<c:forEach items="${boardList }" var="board">
							<li>게시판 이름 :
								<input type="text" name="boardPanName" value="${board.panName }" />
								<select name="boardUse" >
										<option value="y" <c:if test="${board.panDel=='y'}">selected</c:if>>사용</option>
										<option value="n" <c:if test="${board.panDel=='n'}">selected</c:if>>안사용</option>
								</select> 
								<button> 등록</button>
							</li>	
						</c:forEach>	
											
						</ul>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
