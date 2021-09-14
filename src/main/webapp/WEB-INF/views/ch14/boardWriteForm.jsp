<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		글쓰기
	</div>
	<div class="card-body">
		<form id="boardWriteForm" method="POST" action="boardWrite">
		   <!-- <table style="width:auto" class="table table-sm table-bordered"> -->
		   <div class="input-group">
		      <div class="input-group-prepend"><span class="input-group-text">btitle</span></div>
		      <input id="btitle" type="text" name="btitle" class="form-control">
		      <span id="btitleError" class="error"></span>
		   </div>
		   
		   <div class="input-group">
		      <div class="input-group-prepend"><span class="input-group-text">bcontent</span></div>
		      <textarea id="bcontent" name="bcontent" class="form-control"></textarea>
		      <span id="bcontentError" class="error"></span>
		   </div>
		   
		   <div class="input-group">
		      <div class="input-group-prepend"><span class="input-group-text">bwriter</span></div>
		      <input id="writer" type="text" name="mid" class="form-control" 
		         <%-- <c:if test="${sessionMid!=null}">value="${sessionMid}"</c:if>
		         <c:if test="${sessionMid==null}">value="user"</c:if> --%>
		         value="user"
		         readonly>
		      <span id="btitleError" class="error"></span>
		   </div>
		      
		   <div class="mt-3">
		      <button class="btn btn-info btn-sm mr-2" type="submit">글쓰기</button>
		      <a class="btn btn-info btn-sm" href="boardList">취소</a>
		   </div>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>