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
<script type="text/javascript">
//editReply,delReply
	$(document).ready(function(){
		$(".saveReply").on("click", function(){
			var saveReply = $("#saveReplyParam").children(".reple").val();
			$("#addReply").val(saveReply);
			$("#frm").submit();
			//console.log($("#frm").serialize());
		});
	});

	$("#updateButton").on("click", function(){
		//$("#addReply").attr("+[${reply.replyerId}]+" ","${reply.replySub}");
		//$(this).html("<input type='text' id='addReply' class='reple' name='reple' placeholder='댓글을 작성해주세요~'>");
		var updateR = $(".updateR").html();
		$(".updateR").remove();
		$("#reReply").append("<input id='addeply' class='reple' name='"+updateR+"'/>");
		$("#addeply").val(updateR);
		//console.log($("#addReply").serialize());
	});
</script>
</head>

<body>
	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	
	<!-- parameter sender -->
	<form id="frm" action="/boardTextDetail" method="post">
		<input type="hidden" name="userId" value="${S_USER.userId}">	<!-- 매번 불러오기... 최선인가? 다른 방식없나?-->
		<!-- <input id="panId" type="hidden" name="panId" >
		<input id="pan_Name" type="hidden" name="pan_Name" >-->
		<input id="delReply" type="hidden" name="delReply" >
		<input id="textNum" type="hidden" name="textNum" value="${textVo.textNum }"> 
		<input type="hidden" name="addReply" id="addReply" >
	</form>
	
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
						<a class="btn btn-default pull-right" 
							href="/boardTextReplyEditer?userId=${S_USER.userId}&panId=${panVo.panId }&textNum=${textVo.textNum}">답글</a>
							
						<h2 class="sub-header">[${textVo.textNum }]  ${textVo.textName }</h2>
						<div class="table-responsive">
							<div class="form-group heightFix">
								<div class="col-sm-10">
									<label class="control-label">${textVo.textSub }</label>
								</div>
							</div>
							
							<div class="form-group">
								<br /> <br /> 
								<label for="userNm" class="col-sm-10 control-label">[ 첨부파일 ] </label>
								<div class="col-sm-10 ">
									<c:forEach items="${addFileList }" var="addFile" varStatus="i">
											<span>첨부파일 ${i.count } : ${addFile.addFileName} - </span>  <a href="${addFile.addFileUrl}">다운로드</a>
										<br /> 
									</c:forEach>
									<%-- <c:forEach items="${addFileList }" var="addFile" begin=1 end=5 step=1 varStatus="i">
											<p>첨부파일 ${i } : </p>  <a href="${addFile.addFileUrl}">다운로드</a>
										<br /> 
									</c:forEach> --%>
								</div>
							</div>
						</div>
						
						<div class="table-responsive">
							<div class="form-group">
								<div id="saveReplyParam" class="col-sm-10"><br /> 
									<label for="userNm" >[ 댓글 ]</label> 
									<input type="text" id="addReply" class="reple" name="reple" placeholder="댓글을 작성해주세요~">
									<a class="btn btn-default saveReply">저장</a><br />
									<div class="col-sm-10" id="reReply">
									<c:forEach items="${replyList }" var="reply" >
										<label class="control-label updateR"> [${reply.replyerId}] ${reply.replySub} | <fmt:formatDate value="${reply.replyDate}" pattern="yyyy-MM-dd HH:MM"/> </label>
										<!-- <a class="btn btn-default" id="updateButton">수정</a> -->
										<a class="btn btn-default savebutton" href="/boardTextReplyDel?replyId=${reply.replyId}&textNum=${textVo.textNum }&panId=${panVo.panId }">삭제</a><br /> 
									</c:forEach> <br /> 
									</div>
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
