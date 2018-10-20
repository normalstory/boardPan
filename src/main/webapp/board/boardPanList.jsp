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

<title>BoardPanList</title>

<!-- basicLib -->
<%@ include file="/common/basicLib.jsp"%>

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
						<h2 class="sub-header">사용자</h2>

						<div class="table-responsive">

							<table class="table table-striped table-hover">
								<tr>
									<th>게시글번호</th>
									<th>제목</th>
									<th>작성자아이디</th>
									<th>작성일시</th>
								</tr>
								
								<!-- panList loop 출력 -->
								<c:forEach items="${textVoListPage }" var="panList" >
								<tr class="userClick" >
									<td>${panList.textNum }</td>
									<td>${panList.textName }</td>
									<td>${panList.textWriterId }</td>
									<td><fmt:formatDate value="${panList.textWriteDate }" pattern="yyyy-MM-dd"/></td>
								</tr>
								</c:forEach>
								
							</table>
							
						</div>

						<a class="btn btn-default pull-right" href="/boardPanForm">새글 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<li><a href="/boardPanList?page=1&pageSize=10&panId=${panId }" aria-label="PreviousFloor"> 
								<span aria-hidden="true">&laquo;</span></a></li>
								
								<c:forEach begin="1" end="${pageNum }" var="p">
								<li><a href="/boardPanList?page=${p}&pageSize=10&panId=${panId }">${p}</a></li>
								</c:forEach>
								    
								<li><a href="/boardPanList?page=${pageNum }&pageSize=10&panId=${panId }" aria-label="NextFloor"> 
								<span aria-hidden="true">&raquo;</span></a></li>
							</ul>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
