<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>BoardPan Detail</title>
<style type="text/css">
.heightFix{
	/* height: 420px; */
	height: 29em;
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

				<div class="row">
					<div class="col-sm-8 blog-main">
						${panVo.panName }게시판 &#187; <br/>
						작성자 : ${textVo.textWriterId } | 작성일 : <fmt:formatDate value="${textVo.textWriteDate }" pattern="yyyy-MM-dd HH:MM"/> 
						<a class="btn btn-default pull-right" 
							href="/boardTextEditerUpdate?textNum=${textVo.textNum}&panId=${panVo.panId }">수정</a>
						<a class="btn btn-default pull-right" 
							href="/boardTextDel?textNum=${textVo.textNum}&panId=${panVo.panId }">삭제</a>
						<button type="button" class="textReply">답글</button>
						
						<h2 class="sub-header">[${textVo.textNum }]  ${textVo.textName }</h2>
						<div class="table-responsive">
							<div class="form-group heightFix">
								<div class="col-sm-10">
									<label class="control-label">${textVo.textSub }</label>
								</div>
							</div>
							
							<div class="form-group">
								<br /> <br /> <label for="userNm" class="col-sm-10 control-label">첨부파일 : </label>
								<div class="col-sm-10">
									<label class="control-label"> <a href="#">첨부파일 내용 출력 1</a> </label> <br /> 
									<label class="control-label"> <a href="#">첨부파일 내용 출력 2</a> </label> <br />
								</div>
							</div>
						</div>
						
						<div class="table-responsive">
							<div class="form-group">
								<div class="col-sm-10">
									<label for="userNm" >[ 댓글 ]</label><br />
										<input type="text" name="reple"><button>댓글 저장 </button><br />
									<label class="control-label">댓글 내용 1</label> <button>수정</button><button>삭제</button><br /> 
									<label class="control-label">댓글 내용 2</label> <button>수정</button><button>삭제</button><br /> 
								</div>
							</div>
						</div>
						
					</div>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>
