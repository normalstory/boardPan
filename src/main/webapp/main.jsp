<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
							<h2 class="blog-post-title">찬우의 BoardPan 만들기</h2>
							<p>jsp를 통한 게시판구축 실습페이지</p>
							<hr>

							<h3>상세내역</h3>
							<p>JSP과정에서는 다음의 화면을 구축한다.</p>
							<ul>
								<li>로그인 화면</li>
								<li>게시판 관리 화면
									<ul>
										<li>리스트 화면(페이징)</li>
										<li>상세 화면(댓글, 덪글)</li>
										<li>입력 화면(이미지첨부, 첨부파일)</li>
										<li>수정 화면</li>
									</ul>
								</li>
								<li>공통
									<ul>
										<li>각 버튼은 회원 및 데이터 유무 구분</li>
										<li>삭제없고 컬럼에 별도 체크</li>
										<li>메소드별 테스트코드 작성</li>
									</ul>
								</li>
							</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
