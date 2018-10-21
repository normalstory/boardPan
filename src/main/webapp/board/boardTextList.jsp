<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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

<title>BoardTextList</title>

<!-- basicLib -->
<%@ include file="/common/basicLib.jsp"%>

<!-- 사용자 클릭해서 상세화면으로 이동 -->
<style type="text/css">
.heightFix {
	/* height: 420px; */
	height: 29em;
}
.userClick {
	cursor: pointer;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		var ec="click"
		$(".textClick").on(ec,function(){
			var textNum = $(this).children()[0].innerText;
			$("#textNum").val(textNum);
			$("#frm").submit();
		});
		
	});	
</script>

</head>

<form id="frm" action="/boardTextDetail" method="get">
	<input type="hidden" id="textNum" name="textNum">
	<input type="hidden" id="textNum" name="panId" value="${panVo.panId }">
</form>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@ include file="/common/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">${panVo.panName }</h2>

						<div class="table-responsive heightFix">

							<table class="table table-striped table-hover">
								<tr>
									<th>게시글번호</th>
									<th>제목</th>
									<th>작성자아이디</th>
									<th>작성일시</th>
								</tr>
								
								<!-- panList loop 출력 -->
								<c:forEach items="${textVoListPage }" var="panList" >
								<tr class="textClick" >
									<td>${panList.textNum }</td>
									<td>${panList.textName }</td>
									<td>${panList.textWriterId }</td>
									<td><fmt:formatDate value="${panList.textWriteDate }" pattern="yyyy-MM-dd"/></td>
								</tr>
								</c:forEach>
								
							</table>
							
						</div>

						<a class="btn btn-default pull-right" href="/boardTextEditer?userId=${S_USER.userId}&panId=${panVo.panId }">새글 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<li><a href="/boardTextList?page=1&pageSize=10&panId=${panVo.panId }" aria-label="PreviousFloor"> 
								<span aria-hidden="true">&#8676;</span></a></li>
								
								<c:if test="${page!=1}">
								<li><a href="/boardTextList?page=${page-1}&pageSize=10&panId=${panVo.panId }" aria-label="PreviousFloor"> 
								<span aria-hidden="true">&#8592;</span></a></li>
								</c:if>
								
								<c:forEach begin="1" end="${pageNum }" var="p">
								<li><a href="/boardTextList?page=${p}&pageSize=10&panId=${panVo.panId }">${p}</a></li>
								</c:forEach>
								
								<c:if test="${page!=pageNum}">   
								<li><a href="/boardTextList?page=${page+1}&pageSize=10&panId=${panVo.panId }" aria-label="NextFloor"> 
								<span aria-hidden="true">&#8594;</span></a></li>
								</c:if>
								
								<li><a href="/boardTextList?page=${pageNum }&pageSize=10&panId=${panVo.panId }" aria-label="NextFloor"> 
								<span aria-hidden="true">&#8677;</span></a></li> 
							</ul>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
