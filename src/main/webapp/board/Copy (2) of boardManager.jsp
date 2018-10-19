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

<!-- 사용자 클릭해서 상세화면으로 이동 -->
<style type="text/css">
.userClick {
	cursor: pointer;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		var ec="click"
		$(".userClick").on(ec, function(){
			var boardUse = $("#boardUse").children()[1].innerText;
			var boardPanName = $("#boardPanName").children()[1].innerText;
			$("#boardUse").val(boardUse);
			$("#boardPanName").val(boardPanName);
			$("#frm").submit();
		});
	});	
</script>
</head>

<body>
	<!-- parameter sender -->
	<form id="frm" action="/boardManager" method="post">
		<input type="hidden" name="userId" value="${S_USER.userId}">
		<input type="hidden" name="addPan_Name" id="addPan" value="${addPan_Name }">
		<input type="hidden" name="addPan_Del" id="addPan" value="${addPan_Del }">
	</form>
	
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
					<ul>
						<li>게시판 이름 : 
							<input type="text" id="addPan_Name" name="addPan_Name" placeholder="생성할 게시판 이름 기재~" /> 
							<select id="addPan_Del" name="addPan_Del" >
									<option value="y">사용</option>
									<option value="x">안사용</option>
							</select> 
							<button class="userClick">등록</button>
						</li>
					</ul>
					<p>운영 게시판 목록</p>
					<ul>
						<c:forEach items="${boardList }" var="board">
							<li>게시판 이름 : 
								<input type="text" id="boardPanName" name="boardPanName" value="${board.panName }" /> 
								<select id="boardUse" name="boardUse">
										<option value="y"
											<c:if test="${board.panDel=='y'}">selected</c:if>>사용</option>
										<option value="n"
											<c:if test="${board.panDel=='n'}">selected</c:if>>안사용</option>
								</select>
								<button class="userClick" >등록</button>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
