<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="kr.or.ddit.board.model.BoardPanVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
.menuList{
	background-color: #fffddd;
}
</style>  
<script type="text/javascript">
	$(document).ready(function(){
		$(".addBoardPan").on("click", function(){
			var boardManager = $("#addBoardParam").children(".addPan").eq(0).val();
			$("#pan_Name").val(boardManager);
			boardManager = $("#addBoardParam").children(".addPan").eq(1).val();
			$("#pan_Del").val(boardManager);
			$("#pan_Case").val("add");
			$("#frm").submit();
			//console.log($("#frm").serialize());
		});
		 
		$(".updateBoardParam").on("click","#saveMenu", function(){
			//감싸고있는 상위요소.on("","클릭 대상 요소",펑션)
			var boardManager = $(this).parent()[0].children[1].value
			$("#pan_Name").val(boardManager);
			boardManager = $(this).parent()[0].children[2].value
			$("#pan_Del").val(boardManager);
			$("#pan_Case").val("update");
			boardManager = $(this).parent()[0].children[3].value
			$("#panId").val(boardManager);
			$("#frm").submit();
			//console.log($("#frm").serialize());
		});
		
		$(".upPan").on("click",function(){
			$("li").css('background','white');				//구분표시(초기화)
			var upbt=$(this).closest("li");
			upbt.insertBefore(upbt.prev());
			$(this).parent().css("background","#bbe5ff");	//구분표시
		});
		$(".downPan").on("click",function(){
			$("li").css('background','white');				//구분표시(초기화)
			var downbt=$(this).closest("li");
			downbt.insertAfter(downbt.next());
			$(this).parent().css('background','#bbe5ff');	//구분표시	
			//$(this).siblings("input").css("background","#bbe5ff");	//자기 자신까 포함하는 siblings는 어떻게 할 수 있을까?	
		});
		
	});	
</script>
</head>

<body>
	
	
	<!-- header -->
	<%@ include file="/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@ include file="/common/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-5 col-md-offset-2 main">
				<div class="blog-post">
					<h2 class="blog-post-title">BoardManager</h2>
					<p>게시판을 관리하는 페이지 입니다.</p>
					<hr>

					<h3>게시판 목록</h3>
					<span>신규 생성하기</span>
					<ul> 
						<li id="addBoardParam" >
							<input class="addPan" type="text" name="addPan_Name" placeholder="생성할 게시판 이름 기재~" /> 
							<select class="addPan" name="addPan_Del" >
									<option value="y">사용</option>
									<option value="x">안사용</option>
							</select> 
							<button type="button" class="addBoardPan">등록</button>
						</li>
					</ul>
					<br/>
					
					<!-- parameter sender -->
					<form id="frm" action="/boardPanManager" method="post">
						<input type="hidden" name="userId" value="${S_USER.userId}">	<!-- 꼭 필요한가? 다른방식없나?-->
						<input id="panId" type="hidden" name="panId" >
						<!-- <input id="pan_Name" type="hidden" name="pan_Name" >
						<input id="pan_Del" type="hidden" name="pan_Del" > -->
						<input id="pan_Case" type="hidden" name="pan_Case" >
						
						<span>운영 게시판 목록</span>
						<ul class="updateBoardParam" >
							<c:forEach items="${boardList }" var="board" varStatus="i">
								<li >
									<input type="hidden" name="pan_Id" value="${board.panId }" />
									<input type="text" name="pan_Name" value="${board.panName }" 
									<c:if test="${board.panDel=='y'}">class="menuList"</c:if>
									 /> 
									<select name="boardUse">
											<option value="y"
												<c:if test="${board.panDel=='y'}">selected</c:if>>사용</option>
											<option value="n"
												<c:if test="${board.panDel=='n'}">selected</c:if>>안사용</option>
									</select>
									<c:if test="${board.panDel=='y'}">
										<button type="button" class="upPan">&#8593;	</button>
										<button type="button" class="downPan">&#8595; </button>
									</c:if>
								</li>
							</c:forEach>
							<a class="btn btn-default pull-right" id="saveMenu">저장</a>
						</ul>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
