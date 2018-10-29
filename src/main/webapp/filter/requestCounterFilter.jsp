<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

<title>BoardPan</title>

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
					<h2 class="blog-post-title">BoardPan 방문통계</h2>
					<p>회원별 사이트 이용 내역을 확인하는 페이지 입니다.</p>
					<p>[ ${userVo.name } ] 회원</p>
					<hr>
						<ul>
							<li>로그인 
								<c:forEach items="${uriMap }" var="map">
								<c:if test="${map.key eq '/loginServlet' or '/'}"> : ${map.value}</c:if>
								</c:forEach>
							</li>
							<li>메인 
								<c:forEach items="${uriMap }" var="map">
								<c:if test="${map.key eq '/main.jsp'}"> : ${map.value  }</c:if>
								</c:forEach>
							</li>
							<li>게시판 메뉴관리
								<c:forEach items="${uriMap }" var="map">
								<c:if test="${map.key eq '/boardPanManager'}"> : ${map.value  }</c:if>
								</c:forEach>
							</li>
							<li>게시판 통계관리
								<c:forEach items="${uriMap }" var="map">
								<c:if test="${map.key eq '/filter/requestCounterFilter.jsp'}"> : ${map.value  }</c:if>
								</c:forEach>
							</li>
							<li>게시판 목록
								<c:forEach items="${uriMap }" var="map">
								<c:if test="${map.key eq '/boardTextList'}"> : ${map.value  }</c:if>
								</c:forEach>
							</li>
							<li>게시판 상세
								<c:forEach items="${uriMap }" var="map">
								<c:if test="${map.key eq '/boardTextDetail'}"> : ${map.value  }</c:if>
								</c:forEach>
							</li>
							<li>로그아웃
								<c:forEach items="${uriMap }" var="map">
								<c:if test="${map.key eq '/logout'}"> : ${map.value  }</c:if>
								</c:forEach>
							</li>
							<li>기타</li>
						</ul>
						
						<hr/>
						#original list
						<c:forEach items="${uriMap }" var="map">
									${map.key } : ${map.value } <br/>
						</c:forEach>
						
						<hr/>
						#todo list 
						<ol>
							<li> 페이지(li)별fh 반복되고 있는 c:forEach 간추림 방법 for문 말고, while? swich? 는 없을까? </li>
							<li> 하나의 서블릿을 공통으로 사용하고 있는 각각의 게시판들을 나눠서 체크할 수 있는 방법 </li>
							<li> 1번이 가능하다면, 1번에 해당하지않는 항목들을 묶어서 '기타'의 li 로 추가 표시할 수 있는 방법 </li>
						</ol>
				</div>
			</div>
		</div>
	</div>
</body>
</html>